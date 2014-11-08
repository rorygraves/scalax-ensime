scalax-linear-algebra
=====================

Talk for Scala eXchange 2014: Ensime

[Reveal.js](https://github.com/hakimel/reveal.js) and [MathJax](https://github.com/mathjax/MathJax) are bundled as submodules. The easiest way to get them is to clone recursively:

```
git clone --recursive git@github.com:rorygraves/scalax-ensime.git
```

To generate the talk, install [pandoc](http://johnmacfarlane.net/pandoc/) and run `./scalax-linear-algebra.org.pandoc`

Notes
-----

To run the talk with notes enabled, the presentation must be served via grunt.

One-time setup like so (from the `reveal.js` directory):

```
sudo apt-get install npm # install nodejs and its package manager (debian)
sudo ln -s /usr/bin/nodejs /usr/local/bin/node # alias used by phantomjs
sudo npm install -g grunt-cli # install grunt
npm install # install the dependencies of reveal.js
ln -s ../MathJax MathJax # point to our local MathJax
ln -s ../images images # point to our images
```

If you're behind a corporate proxy, you may need to setup npm:

```
# assuming you have set the http_proxy
# Note that HTTPS doesn't really work http://stackoverflow.com/a/11773612/1041691
npm config set proxy $http_proxy
npm config set https-proxy $https_proxy
npm config set registry http://registry.npmjs.org
```

You may need to do a manual install of [phantomjs](http://phantomjs.org/download.html) if it fails to automatically install (and perhaps also `npm install bl mime-types caseless`). https://github.com/ariya/phantomjs/issues/12729

To start the presentation (from the `reveal.js` directory):

```
cat ../scalax-linear-algebra.html | sed 's|reveal.js/||g' > index.html
grunt serve --port 8000
```

Then navigate to [`http://localhost:8000`](http://localhost:8000) and type the letter `s` to open the presenter's window.
