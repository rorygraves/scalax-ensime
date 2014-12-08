./generate-doc.sh scalax-ensime
cd reveal.js
cat ../scalax-ensime.html | sed 's|reveal.js/||g' > index.html
grunt serve --port 8005 &
sleep 1
open http://localhost:8005
wait
