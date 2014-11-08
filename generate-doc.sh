#!/bin/bash

if [ ! -f "$1.md" ] ; then
  echo "input file not provided or does not exist: $1"
  exit 1
else
  echo "running pandoc on $1.md"
fi

pandoc --standalone \
       --smart \
       --write=revealjs \
       --slide-level=2 \
       --variable theme:solarized \
       $1.md \
       --highlight-style=kate \
       --output $1.html
