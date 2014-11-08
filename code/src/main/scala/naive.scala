package com.github.fommil

import scala.util.Random

object MatrixMult extends App {

  case class ImmutableMatrix(
    numRows: Int,
    numCols: Int,
    data: Vector[Vector[Double]]
  ) {
    // def set(row: Int, col: Int, value: Double): ImmutableMatrix = {
    //   val updateRow = data(row).updated(col, value)
    //   val updateData = data.updated(row, updateRow)
    //   copy(data = updateData)
    // }
    // def get(row: Int, col: Int): Double = data(row)(col)
    def mult(that: ImmutableMatrix): ImmutableMatrix = {
      require(numCols == that.numRows)
      val res = Array.fill(numRows, that.numCols)(0.0)
      for {
        i <- 0 until numRows
        j <- 0 until that.numCols
        k <- 0 until numCols
      } {
        res(i)(j) = data(i)(k) * that.data(k)(j)
      }
      ImmutableMatrix(numRows, numCols, ImmutableMatrix.arrayToVec(res))
    }
  }
  object ImmutableMatrix {
    def arrayToVec(data: Array[Array[Double]]): Vector[Vector[Double]] = {
      for {
        row <- data
      } yield row.toVector
    }.toVector

    def random(numRows: Int, numCols: Int): ImmutableMatrix = {
      val res = Array.fill(numRows, numCols)(0.0)
      for {
        i <- 0 until numRows
        j <- 0 until numCols
      } {
        res(i)(j) = Random.nextGaussian()
      }
      ImmutableMatrix(numRows, numCols, arrayToVec(res))
    }
  }

  trait Matrix {
    def numRows: Int
    def numCols: Int
    def set(row: Int, col: Int, value: Double): Unit
    def get(row: Int, col: Int): Double
    def mult(that: Matrix): Matrix
  }
  object Matrix {
    def randomise(mat: Matrix): Unit =
      for {
        i <- 0 until mat.numRows
        j <- 0 until mat.numCols
      } {
        mat.set(i, j, Random.nextGaussian())
      }
  }

  class NaiveMatrix(
    val numRows: Int,
    val numCols: Int,
    val values: Array[Array[Double]]
  ) extends Matrix {
    require(numRows > 0 && numCols > 0)
    def set(row: Int, col: Int, value: Double): Unit =
      values(row)(col) = value

    def get(row: Int, col: Int): Double =
      values(row)(col)

    def mult(that: Matrix): Matrix = {
      val res = NaiveMatrix(numRows, that.numCols)
      for {
        i <- 0 until numRows
        j <- 0 until that.numCols
        k <- 0 until numCols
      } {
        val update = this.get(i, k) * that.get(k, j)
        res.set(i, j, res.get(i, j) + update)
      }
      res
    }
  }

  object NaiveMatrix {
    def apply(numRows: Int, numCols: Int): NaiveMatrix = {
      new NaiveMatrix(numRows, numCols, Array.fill(numRows, numCols)(0.0))
    }

    def random(numRows: Int, numCols: Int): NaiveMatrix = {
      val mat = apply(numRows, numCols)
      Matrix.randomise(mat)
      mat
    }
  }

  trait NaiveParallelMatrix {
    this: NaiveMatrix =>
    override def mult(that: Matrix): Matrix = {
      val res = NaiveMatrix(numRows, that.numCols)
      for {
        i <- (0 until numRows).par
        j <- (0 until that.numCols).par
        k <- 0 until numCols
      } {
        val update = this.get(i, k) * that.get(k, j)
        res.set(i, j, res.get(i, j) + update)
      }
      res
    }
  }
  object NaiveParallelMatrix {
    def apply(numRows: Int, numCols: Int): NaiveMatrix =
      new NaiveMatrix(
        numRows, numCols, Array.fill(numRows, numCols)(0.0)
      ) with NaiveParallelMatrix
    def random(numRows: Int, numCols: Int): NaiveMatrix = {
      val mat = apply(numRows, numCols)
      Matrix.randomise(mat)
      mat
    }
  }

  trait NaiveWhileMatrix {
    this: NaiveMatrix =>
    override def mult(that: Matrix): Matrix = {
      val res = NaiveMatrix(numRows, that.numCols)
      var i, j, k = 0
      while (i < numRows) {
        j = 0
        while (j < that.numCols) {
          k = 0
          var sum = 0.0
          while (k < numCols) {
            sum += this.get(i, k) * that.get(k, j)
            k += 1
          }
          res.set(i, j, sum)
          j += 1
        }
        i += 1
      }
      res
    }
  }
  object NaiveWhileMatrix {
    def apply(numRows: Int, numCols: Int): NaiveMatrix =
      new NaiveMatrix(
        numRows, numCols, Array.fill(numRows, numCols)(0.0)
      ) with NaiveWhileMatrix
    def random(numRows: Int, numCols: Int): NaiveMatrix = {
      val mat = apply(numRows, numCols)
      Matrix.randomise(mat)
      mat
    }
  }

  def timed[T](name: String)(f: => T): T = {
    val t0 = System.nanoTime()
    val res = f
    val t1 = System.nanoTime()
    println(name + (t1 - t0))
    res
  }

  val factor = 3 / 100.0

  for {
    i <- (1 to 100).reverse
    size = math.pow(10, factor * i).toInt
    if size >= 10
  } {
    val a = NaiveMatrix.random(size, size)
    val b = NaiveParallelMatrix.random(size, size)
    val c = NaiveWhileMatrix.random(size, size)

    if (size < 500) {
      // too painful!
      val i = ImmutableMatrix.random(size, size)
      timed("IMMUT: " + size + ",") {
        i mult i
      }
    }
    timed("NAIVE:" + size + ",") {
      a mult a
    }
    timed("PAR:" + size + ",") {
      b mult b
    }
    timed("WHILE: " + size + ",") {
      c mult c
    }
  }


}
