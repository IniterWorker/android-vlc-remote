# VLC Http API

### Usefull URLs

Replace ${addr} by ip address.

```
http://${addr}:8080/requests/status.json
```
-> Get status (current elem played, is in fullscreen, volume, loop, etc).
```
http://${addr}:8080/requests/playlist.json
```
-> Get playlist.
```
http://${addr}:8080/requests/browse.json
```
-> Allow to browse files. Add param "uri=file:///..." to choose directory to browse.

### Command

Commands could be passed, using status url for exemple.
Command template : `http://${addr}:8080/requests/status.json?command=VALUE`

With VALUE :

| VALUE | Effect | Additional required param |
|:-:|:-:|:-:|
|pl_next|Jump to next elem|*none*|
|pl_previous|Jump to previous elem|*none*|
|pl_pause|Toggle play/pause|*none*|
|pl_stop|Stop playing|*none*|
|pl_play|Play a specific elem in playlist|id=*\${id}* (\${id} -> elem id) |
|in_play|Play a file not in playlist (and add it to playlist)|input=*file:///path/to/file*|
|fullscreen|Toggle fullscreen|*none*|
|seek|`Move to a given moment. value must be [+ or -][<int><H or h>:][<int><M or m or '>:][<int><nothing or S or s or ">] or [+ or -]<int>% (value between [ ] are optional, value between < > are mandatory)`| val=*value*|
|volume|Change Volume (between 0 and 512). Can be percent (<int>%), absolute (<int>) or relative (+/-<int>)|val=*volume_value*|
|subdelay|Add delay for subtitles|val=*delay* (delay is in sec)|
|audiodelay|Add delay for audio|val=*delay* (delay is in sec)|
|rate|Change speed|val=*speed* (normal speed is 1)|
|preamp|For equalizer. Dunno what is it| val=*value*|
|pl_loop|Toggle loop (loop on playlist)|*none*|
|pl_repeat|Toggle repeat (loop on current elem)|*none*|
|pl_random|Toggle random|*none*|
|pl_empty|Empty playlist|*none*|
|in_enqueue|Add file to playlist.|input=*file:///path/to/file*|
|addsubtitle|Add subtitle to currently playing file|val=*file:///path/to/file*|
|pl_forceresume|Force play : if already in play, do nothing|*none*|
|pl_forcepause|Force pause : if already in pause, do nothing|*none*|
|pl_delete|Delete item from playlist|id=*\${id}* (\${id} -> elem id) |
|aspectratio|Set acpect ratio. Value must be 1:1 , 4:3 , 5:4 , 16:9 , 16:10 , 221:100 , 235:100 , 239:100 |val=*value*|
|pl_sort|Sort playlist in function of id and val. id 0 -> normal, 1 -> reverse. val 0 -> by id, 1 -> by name, 3 -> by author, 5 -> random, 7 by track number|id=*id* val=*val*|
