## [CompressedObject](../jquery/jszip/dist/jszip.js#L131)

**Type:** `function`

Represent a compressed object, with everything needed to decompress it. 




|Parameter Name|Description|
|-----|-----|
||
||
||
||
||








## [getContentWorker](../jquery/jszip/dist/jszip.js#L149)

Create a worker to get the uncompressed content. 





**Returned Value:**  








## [getCompressedWorker](../jquery/jszip/dist/jszip.js#L166)

Create a worker to get the compressed content. 





**Returned Value:**  








## [CompressedObject.createWorkerFrom](../jquery/jszip/dist/jszip.js#L180)

Chain the given worker with other workers to compress the content with the 
given compresion. 




|Parameter Name|Description|
|-----|-----|
||
||
||


**Returned Value:** 








## [](../jquery/jszip/dist/jszip.js#L220)

The following functions come from pako, from pako/lib/zlib/crc32.js 
released under the MIT license, see pako https://github.com/nodeca/pako/ 












## [crc32str](../jquery/jszip/dist/jszip.js#L258)

**Type:** `function`

Compute the crc32 of a string. 
This is almost the same as the function crc32, but for strings. Using the 
same function for the two use cases leads to horrible performances. 




|Parameter Name|Description|
|-----|-----|
||
||
||
||


**Returned Value:** 








## [module.exports](../jquery/jszip/dist/jszip.js#L321)

Let the user use/change some implementations. 












## [FlateWorker](../jquery/jszip/dist/jszip.js#L340)

**Type:** `function`

Create a worker that uses pako to inflate/deflate. 




|Parameter Name|Description|
|-----|-----|
||
||








## [FlateWorker.prototype.processChunk](../jquery/jszip/dist/jszip.js#L359)









**Reference:** GenericWorker.processChunk 





## [FlateWorker.prototype.flush](../jquery/jszip/dist/jszip.js#L370)









**Reference:** GenericWorker.flush 





## [FlateWorker.prototype.cleanUp](../jquery/jszip/dist/jszip.js#L380)









**Reference:** GenericWorker.cleanUp 





## [FlateWorker.prototype.](../jquery/jszip/dist/jszip.js#L388)

Create the _pako object. 
TODO: lazy-loading this object isn't the best solution but it's the 
quickest. The best solution is to lazy-load the worker list. See also the 
issue #446. 












## [decToHex](../jquery/jszip/dist/jszip.js#L424)

**Type:** `var`

Transform an integer into a string in hexadecimal. 





|Parameter Name|Description|
|-----|-----|
||
||








## [generateUnixExternalFileAttr](../jquery/jszip/dist/jszip.js#L440)

**Type:** `var`

Generate the UNIX part of the external file attributes. 




|Parameter Name|Description|
|-----|-----|
||
||


**Returned Value:**  adapted from http://unix.stackexchange.com/questions/14705/the-zip-formats-external-file-attribute :  TTTTsstrwxrwxrwx0000000000ADVSHR ^^^^____________________________ file type, see zipinfo.c (UNX_) ^^^_________________________ setuid, setgid, sticky ^^^^^^^^^________________ permissions ^^^^^^^^^^______ not used ? ^^^^^^ DOS attribute bits : Archive, Directory, Volume label, System file, Hidden, Read only 








## [generateDosExternalFileAttr](../jquery/jszip/dist/jszip.js#L467)

**Type:** `var`

Generate the DOS part of the external file attributes. 




|Parameter Name|Description|
|-----|-----|
||
||


**Returned Value:**  Bit 0 Read-Only Bit 1 Hidden Bit 2 System Bit 3 Volume Label Bit 4 Directory Bit 5 Archive 








## [generateZipParts](../jquery/jszip/dist/jszip.js#L486)

**Type:** `var`

Generate the various parts used in the construction of the final zip file. 




|Parameter Name|Description|
|-----|-----|
||
||
||
||
||
||


**Returned Value:** 








## [generateCentralDirectoryEnd](../jquery/jszip/dist/jszip.js#L674)

**Type:** `var`

Generate the EOCD record. 




|Parameter Name|Description|
|-----|-----|
||
||
||
||
||


**Returned Value:** 








## [generateDataDescriptors](../jquery/jszip/dist/jszip.js#L709)

**Type:** `var`

Generate data descriptors for a file entry. 




|Parameter Name|Description|
|-----|-----|
|on|the file entry.|


**Returned Value:** 








## [ZipFileWorker](../jquery/jszip/dist/jszip.js#L729)

**Type:** `function`

A worker to concatenate other workers to create a zip file. 




|Parameter Name|Description|
|-----|-----|
|`false`|to accumulate it.|
||
||
||








## [ZipFileWorker.prototype.push](../jquery/jszip/dist/jszip.js#L772)









**Reference:** GenericWorker.push 





## [ZipFileWorker.prototype.openedSource](../jquery/jszip/dist/jszip.js#L796)

The worker started a new source (an other worker). 




|Parameter Name|Description|
|-----|-----|
||








## [ZipFileWorker.prototype.closedSource](../jquery/jszip/dist/jszip.js#L819)

The worker finished a source (an other worker). 




|Parameter Name|Description|
|-----|-----|
||








## [ZipFileWorker.prototype.flush](../jquery/jszip/dist/jszip.js#L849)









**Reference:** GenericWorker.flush 





## [ZipFileWorker.prototype.prepareNextSource](../jquery/jszip/dist/jszip.js#L871)

Prepare the next source to be read. 












## [ZipFileWorker.prototype.registerPrevious](../jquery/jszip/dist/jszip.js#L884)









**Reference:** GenericWorker.registerPrevious 





## [ZipFileWorker.prototype.resume](../jquery/jszip/dist/jszip.js#L908)









**Reference:** GenericWorker.resume 





## [ZipFileWorker.prototype.error](../jquery/jszip/dist/jszip.js#L926)









**Reference:** GenericWorker.error 





## [ZipFileWorker.prototype.lock](../jquery/jszip/dist/jszip.js#L944)









**Reference:** GenericWorker.lock 





## [getCompression](../jquery/jszip/dist/jszip.js#L963)

**Type:** `var`

Find the compression to use. 




|Parameter Name|Description|
|-----|-----|
||
||


**Returned Value:** 








## [exports.generateWorker](../jquery/jszip/dist/jszip.js#L979)

Create a worker to generate a zip file. 




|Parameter Name|Description|
|-----|-----|
||
||
||








## [JSZip](../jquery/jszip/dist/jszip.js#L1019)

**Type:** `function`

Representation a of zip file in js 












## [checkEntryCRC32](../jquery/jszip/dist/jszip.js#L1080)

**Type:** `function`

Check the CRC32 of an entry. 




|Parameter Name|Description|
|-----|-----|
||


**Returned Value:** 








## [NodejsStreamInputAdapter](../jquery/jszip/dist/jszip.js#L1160)

**Type:** `function`

A worker that use a nodejs stream as source. 




|Parameter Name|Description|
|-----|-----|
||
||








## [NodejsStreamInputAdapter.prototype.](../jquery/jszip/dist/jszip.js#L1174)

Prepare the stream and bind the callbacks on it. 
Do this ASAP on node 0.10 ! A lazy binding doesn't always work. 




|Parameter Name|Description|
|-----|-----|
||








## [](../jquery/jszip/dist/jszip.js#L1238)

A nodejs stream using a worker as source. 




|Parameter Name|Description|
|-----|-----|
||
||
||





**Reference:** the SourceWrapper in http://nodejs.org/api/stream.html





## [isNode](../jquery/jszip/dist/jszip.js#L1278)

True if this is running in Nodejs, will be undefined in a browser. 
In a browser, browserify won't include this file and the whole module 
will be resolved an empty object. 












## [newBufferFrom](../jquery/jszip/dist/jszip.js#L1284)

Create a new nodejs Buffer from an existing content. 




|Parameter Name|Description|
|-----|-----|
||
||


**Returned Value:**  








## [allocBuffer](../jquery/jszip/dist/jszip.js#L1302)

Create a new nodejs Buffer with the specified size. 




|Parameter Name|Description|
|-----|-----|
||


**Returned Value:**  








## [isBuffer](../jquery/jszip/dist/jszip.js#L1316)

Find out if an object is a Buffer. 




|Parameter Name|Description|
|-----|-----|
||


**Returned Value:**  








## [fileAdd](../jquery/jszip/dist/jszip.js#L1347)

**Type:** `var`

Add a file in the current folder. 




|Parameter Name|Description|
|-----|-----|
||
||
||


**Returned Value:** 








## [parentFolder](../jquery/jszip/dist/jszip.js#L1436)

**Type:** `var`

Find the parent folder of the path. 




|Parameter Name|Description|
|-----|-----|
||


**Returned Value:** 








## [forceTrailingSlash](../jquery/jszip/dist/jszip.js#L1450)

**Type:** `var`

Returns the path with a slash at the end. 




|Parameter Name|Description|
|-----|-----|
||


**Returned Value:** 








## [folderAdd](../jquery/jszip/dist/jszip.js#L1464)

**Type:** `var`

Add a (sub) folder in the current folder. 




|Parameter Name|Description|
|-----|-----|
||
|folders.|Defaults to false.|


**Returned Value:** 








## [](../jquery/jszip/dist/jszip.js#L1487)

Cross-window, cross-Node-context regular expression detection 




|Parameter Name|Description|
|-----|-----|
||


**Returned Value:** false otherwise








## [load](../jquery/jszip/dist/jszip.js#L1499)









**Reference:** loadAsync  





## [forEach](../jquery/jszip/dist/jszip.js#L1507)

Call a callback function for each entry at this folder level. 




|Parameter Name|Description|
|-----|-----|
|function|(relativePath, file) {...} It takes 2 arguments : the relative path and the file.  |








## [filter](../jquery/jszip/dist/jszip.js#L1527)

Filter nested files/folders with the specified function. 




|Parameter Name|Description|
|-----|-----|
|function|(relativePath, file) {...} It takes 2 arguments : the relative path and the file.|


**Returned Value:**  








## [file](../jquery/jszip/dist/jszip.js#L1545)

Add a file to the zip file, or search a file. 




|Parameter Name|Description|
|-----|-----|
||the name of the file to find (if no data) or a regex to match files.|
||
||


**Returned Value:**  a file (when searching by string) or an array of files (when searching by regex).  








## [folder](../jquery/jszip/dist/jszip.js#L1578)

Add a directory to the zip file, or search. 




|Parameter Name|Description|
|-----|-----|
||


**Returned Value:**   








## [remove](../jquery/jszip/dist/jszip.js#L1604)

Delete a file, or a directory and all sub-files, from the zip 




|Parameter Name|Description|
|-----|-----|
||


**Returned Value:**  








## [generate](../jquery/jszip/dist/jszip.js#L1636)

Generate the complete zip file 




|Parameter Name|Description|
|-----|-----|
|-|compression, "STORE" by default. - type, "base64" by default. Values are : string, base64, uint8array, arraybuffer, blob.|


**Returned Value:**  








## [generateInternalStream](../jquery/jszip/dist/jszip.js#L1647)

Generate the complete zip file as an internal stream. 




|Parameter Name|Description|
|-----|-----|
|-|compression, "STORE" by default. - type, "base64" by default. Values are : string, base64, uint8array, arraybuffer, blob.|


**Returned Value:**  








## [generateAsync](../jquery/jszip/dist/jszip.js#L1703)

Generate the complete zip file asynchronously. 








**Reference:** generateInternalStream  





## [generateNodeStream](../jquery/jszip/dist/jszip.js#L1710)

Generate the complete zip file asynchronously. 








**Reference:** generateInternalStream  





## [ArrayReader.prototype.byteAt](../jquery/jszip/dist/jszip.js#L1747)









**Reference:** DataReader.byteAt 





## [ArrayReader.prototype.lastIndexOfSignature](../jquery/jszip/dist/jszip.js#L1753)









**Reference:** DataReader.lastIndexOfSignature 





## [ArrayReader.prototype.readAndCheckSignature](../jquery/jszip/dist/jszip.js#L1769)









**Reference:** DataReader.readAndCheckSignature 





## [ArrayReader.prototype.readData](../jquery/jszip/dist/jszip.js#L1780)









**Reference:** DataReader.readData 





## [checkOffset](../jquery/jszip/dist/jszip.js#L1805)

Check that the offset will not go too far. 




|Parameter Name|Description|
|-----|-----|
||




|Exception|Description|
|-----|-----|
|||





## [checkIndex](../jquery/jszip/dist/jszip.js#L1813)

Check that the specified index will not be too far. 




|Parameter Name|Description|
|-----|-----|
||




|Exception|Description|
|-----|-----|
|||





## [setIndex](../jquery/jszip/dist/jszip.js#L1823)

Change the index. 




|Parameter Name|Description|
|-----|-----|
||




|Exception|Description|
|-----|-----|
|||





## [skip](../jquery/jszip/dist/jszip.js#L1832)

Skip the next n bytes. 




|Parameter Name|Description|
|-----|-----|
||




|Exception|Description|
|-----|-----|
|||





## [byteAt](../jquery/jszip/dist/jszip.js#L1840)

Get the byte at the specified index. 




|Parameter Name|Description|
|-----|-----|
||


**Returned Value:**  








## [readInt](../jquery/jszip/dist/jszip.js#L1848)

Get the next number with a given byte size. 




|Parameter Name|Description|
|-----|-----|
||


**Returned Value:**  








## [readString](../jquery/jszip/dist/jszip.js#L1863)

Get the next string with a given byte size. 




|Parameter Name|Description|
|-----|-----|
||


**Returned Value:**  








## [readData](../jquery/jszip/dist/jszip.js#L1871)

Get raw data without conversion, <size> bytes. 




|Parameter Name|Description|
|-----|-----|
||


**Returned Value:**  








## [lastIndexOfSignature](../jquery/jszip/dist/jszip.js#L1879)

Find the last occurence of a zip signature (4 bytes). 




|Parameter Name|Description|
|-----|-----|
||


**Returned Value:**  








## [readAndCheckSignature](../jquery/jszip/dist/jszip.js#L1887)

Read the signature (4 bytes) at the current position and compare it with sig. 




|Parameter Name|Description|
|-----|-----|
||


**Returned Value:**  








## [readDate](../jquery/jszip/dist/jszip.js#L1895)

Get the next date. 





**Returned Value:**  








## [NodeBufferReader.prototype.readData](../jquery/jszip/dist/jszip.js#L1922)









**Reference:** DataReader.readData 





## [StringReader.prototype.byteAt](../jquery/jszip/dist/jszip.js#L1942)









**Reference:** DataReader.byteAt 





## [StringReader.prototype.lastIndexOfSignature](../jquery/jszip/dist/jszip.js#L1948)









**Reference:** DataReader.lastIndexOfSignature 





## [StringReader.prototype.readAndCheckSignature](../jquery/jszip/dist/jszip.js#L1954)









**Reference:** DataReader.readAndCheckSignature 





## [StringReader.prototype.readData](../jquery/jszip/dist/jszip.js#L1961)









**Reference:** DataReader.readData 





## [Uint8ArrayReader.prototype.readData](../jquery/jszip/dist/jszip.js#L1982)









**Reference:** DataReader.readData 





## [module.exports](../jquery/jszip/dist/jszip.js#L2007)

Create a reader adapted to the data. 




|Parameter Name|Description|
|-----|-----|
||


**Returned Value:** 








## [ConvertWorker](../jquery/jszip/dist/jszip.js#L2042)

**Type:** `function`

A worker which convert chunks to a specified type. 




|Parameter Name|Description|
|-----|-----|
||








## [ConvertWorker.prototype.processChunk](../jquery/jszip/dist/jszip.js#L2053)









**Reference:** GenericWorker.processChunk 





## [Crc32Probe](../jquery/jszip/dist/jszip.js#L2071)

**Type:** `function`

A worker which calculate the crc32 of the data flowing through. 












## [Crc32Probe.prototype.processChunk](../jquery/jszip/dist/jszip.js#L2081)









**Reference:** GenericWorker.processChunk 





## [DataLengthProbe](../jquery/jszip/dist/jszip.js#L2096)

**Type:** `function`

A worker which calculate the total length of the data flowing through. 




|Parameter Name|Description|
|-----|-----|
||








## [DataLengthProbe.prototype.processChunk](../jquery/jszip/dist/jszip.js#L2108)









**Reference:** GenericWorker.processChunk 





## [DataWorker](../jquery/jszip/dist/jszip.js#L2131)

**Type:** `function`

A worker that reads a content and emits chunks. 




|Parameter Name|Description|
|-----|-----|
||








## [DataWorker.prototype.cleanUp](../jquery/jszip/dist/jszip.js#L2162)









**Reference:** GenericWorker.cleanUp 





## [DataWorker.prototype.resume](../jquery/jszip/dist/jszip.js#L2170)









**Reference:** GenericWorker.resume 





## [DataWorker.prototype.](../jquery/jszip/dist/jszip.js#L2185)

Trigger a tick a schedule an other call to this function. 












## [DataWorker.prototype.](../jquery/jszip/dist/jszip.js#L2200)

Read and push a chunk. 












## [GenericWorker](../jquery/jszip/dist/jszip.js#L2242)

**Type:** `function`

A worker that does nothing but passing chunks to the next one. This is like 
a nodejs stream but with some differences. On the good side : 
- it works on IE 6-9 without any issue / polyfill 
- it weights less than the full dependencies bundled with browserify 
- it forwards errors (no need to declare an error handler EVERYWHERE) 

A chunk is an object with 2 attributes : `meta` and `data`. The former is an 
object containing anything (`percent` for example), see each worker for more 
details. The latter is the real data (String, Uint8Array, etc). 





|Parameter Name|Description|
|-----|-----|
||








## [push](../jquery/jszip/dist/jszip.js#L2282)

Push a chunk to the next workers. 




|Parameter Name|Description|
|-----|-----|
|||








## [end](../jquery/jszip/dist/jszip.js#L2289)

End the stream. 





**Returned Value:**  








## [error](../jquery/jszip/dist/jszip.js#L2308)

End the stream with an error. 




|Parameter Name|Description|
|-----|-----|
||


**Returned Value:**  








## [on](../jquery/jszip/dist/jszip.js#L2336)

Add a callback on an event. 




|Parameter Name|Description|
|-----|-----|
||
||


**Returned Value:**  








## [cleanUp](../jquery/jszip/dist/jszip.js#L2346)

Clean any references when a worker is ending. 












## [emit](../jquery/jszip/dist/jszip.js#L2353)

Trigger an event. This will call registered callback with the provided arg. 




|Parameter Name|Description|
|-----|-----|
||
|||








## [pipe](../jquery/jszip/dist/jszip.js#L2365)

Chain a worker with an other. 




|Parameter Name|Description|
|-----|-----|
||


**Returned Value:**  








## [registerPrevious](../jquery/jszip/dist/jszip.js#L2373)

Same as `pipe` in the other direction. 
Using an API with `pipe(next)` is very easy. 
Implementing the API with the point of view of the next one registering 
a source is easier, see the ZipFileWorker. 




|Parameter Name|Description|
|-----|-----|
||


**Returned Value:**  








## [pause](../jquery/jszip/dist/jszip.js#L2403)

Pause the stream so it doesn't send events anymore. 





**Returned Value:**  








## [resume](../jquery/jszip/dist/jszip.js#L2418)

Resume a paused stream. 





**Returned Value:**  








## [flush](../jquery/jszip/dist/jszip.js#L2440)

Flush any remaining bytes as the stream is ending. 












## [processChunk](../jquery/jszip/dist/jszip.js#L2444)

Process a chunk. This is usually the method overridden. 




|Parameter Name|Description|
|-----|-----|
|||








## [withStreamInfo](../jquery/jszip/dist/jszip.js#L2451)

Add a key/value to be added in the workers chain streamInfo once activated. 




|Parameter Name|Description|
|-----|-----|
||
||


**Returned Value:**  








## [mergeStreamInfo](../jquery/jszip/dist/jszip.js#L2462)

Merge this worker's streamInfo into the chain's streamInfo. 












## [lock](../jquery/jszip/dist/jszip.js#L2474)

Lock the stream to prevent further updates on the workers chain. 
After calling this method, all calls to pipe will fail. 












## [toString](../jquery/jszip/dist/jszip.js#L2488)


Pretty print the workers chain. 












## [transformZipOutput](../jquery/jszip/dist/jszip.js#L2521)

**Type:** `function`

Apply the final transformation of the data. If the user wants a Blob for 
example, it's easier to work with an U8intArray and finally do the 
ArrayBuffer/Blob conversion. 




|Parameter Name|Description|
|-----|-----|
||
||
||


**Returned Value:** 








## [concat](../jquery/jszip/dist/jszip.js#L2541)

**Type:** `function`

Concatenate an array of data of the given type. 




|Parameter Name|Description|
|-----|-----|
||
||


**Returned Value:** undefined




|Exception|Description|
|-----|-----|
|Error|if the asked type is unsupported |





## [accumulate](../jquery/jszip/dist/jszip.js#L2572)

**Type:** `function`

Listen a StreamHelper, accumulate its content and concatenate it into a 
complete block. 




|Parameter Name|Description|
|-----|-----|
||
|with|one arg : - the metadata linked to the update received.|


**Returned Value:** Promise the promise for the accumulation. 








## [StreamHelper](../jquery/jszip/dist/jszip.js#L2611)

**Type:** `function`

An helper to easily use workers outside of JSZip. 




|Parameter Name|Description|
|-----|-----|
||
||
||








## [accumulate](../jquery/jszip/dist/jszip.js#L2649)

Listen a StreamHelper, accumulate its content and concatenate it into a 
complete block. 




|Parameter Name|Description|
|-----|-----|
||


**Returned Value:** Promise the promise for the accumulation.  








## [on](../jquery/jszip/dist/jszip.js#L2658)

Add a listener on an event triggered on a stream. 




|Parameter Name|Description|
|-----|-----|
||
||


**Returned Value:**  








## [resume](../jquery/jszip/dist/jszip.js#L2678)

Resume the flow of chunks. 





**Returned Value:**  








## [pause](../jquery/jszip/dist/jszip.js#L2686)

Pause the flow of chunks. 





**Returned Value:**  








## [toNodejsStream](../jquery/jszip/dist/jszip.js#L2694)

Return a nodejs stream for this helper. 




|Parameter Name|Description|
|-----|-----|
||


**Returned Value:**  








## [](../jquery/jszip/dist/jszip.js#L2766)

The following functions come from pako, from pako/lib/utils/strings 
released under the MIT license, see pako https://github.com/nodeca/pako/ 












## [exports.utf8encode](../jquery/jszip/dist/jszip.js#L2921)

Transform a javascript string into an array (typed if possible) of bytes, 
UTF-8 encoded. 




|Parameter Name|Description|
|-----|-----|
||


**Returned Value:** 








## [exports.utf8decode](../jquery/jszip/dist/jszip.js#L2936)

Transform a bytes array (or a representation) representing an UTF-8 encoded 
string into a javascript string. 




|Parameter Name|Description|
|-----|-----|
||


**Returned Value:** 








## [Utf8DecodeWorker](../jquery/jszip/dist/jszip.js#L2952)

**Type:** `function`

A worker to decode utf8 encoded binary chunks into string chunks. 












## [Utf8DecodeWorker.prototype.processChunk](../jquery/jszip/dist/jszip.js#L2963)









**Reference:** GenericWorker.processChunk 





## [Utf8DecodeWorker.prototype.flush](../jquery/jszip/dist/jszip.js#L3001)









**Reference:** GenericWorker.flush 





## [Utf8EncodeWorker](../jquery/jszip/dist/jszip.js#L3015)

**Type:** `function`

A worker to endcode string chunks into utf8 encoded binary chunks. 












## [Utf8EncodeWorker.prototype.processChunk](../jquery/jszip/dist/jszip.js#L3024)









**Reference:** GenericWorker.processChunk 





## [string2binary](../jquery/jszip/dist/jszip.js#L3045)

**Type:** `function`

Convert a string that pass as a "binary string": it should represent a byte 
array but may have > 255 char codes. Be sure to take only the first byte 
and returns the byte array. 




|Parameter Name|Description|
|-----|-----|
||


**Returned Value:** 








## [exports.newBlob](../jquery/jszip/dist/jszip.js#L3062)

Create a new blob with the given content and the given type. 




|Parameter Name|Description|
|-----|-----|
|an|Uint8Array because the stock browser of android 4 won't accept it (it will be silently converted to a string, "[object Uint8Array]").  Use only ONE part to build the blob to avoid a memory leak in IE11 / Edge: when a large amount of Array is used to create the Blob, the amount of memory consumed is nearly 100 times the original data amount. |
||


**Returned Value:** 








## [identity](../jquery/jszip/dist/jszip.js#L3102)

**Type:** `function`

The identity function. 




|Parameter Name|Description|
|-----|-----|
||


**Returned Value:** 








## [stringToArrayLike](../jquery/jszip/dist/jszip.js#L3111)

**Type:** `function`

Fill in an array with a string. 




|Parameter Name|Description|
|-----|-----|
||
||


**Returned Value:** 








## [arrayToStringHelper](../jquery/jszip/dist/jszip.js#L3124)

**Type:** `var`

An helper for the function arrayLikeToString. 
This contains static informations and functions that 
can be optimized by the browser JIT compiler. 












## [stringifyByChunk](../jquery/jszip/dist/jszip.js#L3130)

Transform an array of int into a string, chunk by chunk. 
See the performances notes on arrayLikeToString. 




|Parameter Name|Description|
|-----|-----|
||
||
||


**Returned Value:** undefined




|Exception|Description|
|-----|-----|
|Error|if the chunk is too big for the stack.  |





## [stringifyByChar](../jquery/jszip/dist/jszip.js#L3156)

Call String.fromCharCode on every item in the array. 
This is the naive implementation, which generate A LOT of intermediate string. 
This should be used when everything else fail. 




|Parameter Name|Description|
|-----|-----|
||


**Returned Value:**  








## [uint8array](../jquery/jszip/dist/jszip.js#L3171)

true if the browser accepts to use String.fromCharCode on Uint8Array 












## [nodebuffer](../jquery/jszip/dist/jszip.js#L3181)

true if the browser accepts to use String.fromCharCode on nodejs Buffer. 












## [arrayLikeToString](../jquery/jszip/dist/jszip.js#L3194)

**Type:** `function`

Transform an array-like object to a string. 




|Parameter Name|Description|
|-----|-----|
||


**Returned Value:** 








## [arrayLikeToArrayLike](../jquery/jszip/dist/jszip.js#L3237)

**Type:** `function`

Copy the data from an array-like to an other array-like. 




|Parameter Name|Description|
|-----|-----|
||
||


**Returned Value:** 








## [exports.transformTo](../jquery/jszip/dist/jszip.js#L3332)

Transform an input into any type. 
The supported output type are : string, array, uint8array, arraybuffer, nodebuffer. 
If no output type is specified, the unmodified input will be returned. 




|Parameter Name|Description|
|-----|-----|
||
||




|Exception|Description|
|-----|-----|
||





## [exports.getTypeOf](../jquery/jszip/dist/jszip.js#L3355)

Return the type of the input. 
The type will be in a format valid for JSZip.utils.transformTo : string, array, uint8array, arraybuffer. 




|Parameter Name|Description|
|-----|-----|
||


**Returned Value:** 








## [exports.checkSupport](../jquery/jszip/dist/jszip.js#L3379)

Throw an exception if the type is not supported. 




|Parameter Name|Description|
|-----|-----|
||




|Exception|Description|
|-----|-----|
||





## [exports.pretty](../jquery/jszip/dist/jszip.js#L3394)

Prettify a string read as binary. 




|Parameter Name|Description|
|-----|-----|
||


**Returned Value:** 








## [exports.delay](../jquery/jszip/dist/jszip.js#L3409)

Defer the call of a function. 




|Parameter Name|Description|
|-----|-----|
||
||








## [exports.inherits](../jquery/jszip/dist/jszip.js#L3420)

Extends a prototype with an other, without calling a constructor with 
side effects. Inspired by nodejs' `utils.inherits` 




|Parameter Name|Description|
|-----|-----|
||
||








## [exports.extend](../jquery/jszip/dist/jszip.js#L3432)

Merge the objects passed as parameters into a new one. 




|Parameter Name|Description|
|-----|-----|
||


**Returned Value:** 








## [exports.prepareContent](../jquery/jszip/dist/jszip.js#L3450)

Transform arbitrary content into a Promise. 




|Parameter Name|Description|
|-----|-----|
||
||
||
||
||


**Returned Value:** 








## [ZipEntries](../jquery/jszip/dist/jszip.js#L3522)

**Type:** `function`

All the entries in the zip file. 




|Parameter Name|Description|
|-----|-----|
||








## [checkSignature](../jquery/jszip/dist/jszip.js#L3532)

Check that the reader is on the specified signature. 




|Parameter Name|Description|
|-----|-----|
||




|Exception|Description|
|-----|-----|
|||





## [isSignature](../jquery/jszip/dist/jszip.js#L3544)

Check if the given signature is at the given index. 




|Parameter Name|Description|
|-----|-----|
||
||


**Returned Value:**  








## [readBlockEndOfCentral](../jquery/jszip/dist/jszip.js#L3558)

Read the end of the central directory. 












## [readBlockZip64EndOfCentral](../jquery/jszip/dist/jszip.js#L3580)

Read the end of the Zip 64 central directory. 
Not merged with the method readEndOfCentral : 
The end of central can coexist with its Zip64 brother, 
I don't want to read the wrong number of bytes ! 












## [readBlockZip64EndOfCentralLocator](../jquery/jszip/dist/jszip.js#L3615)

Read the end of the Zip 64 central directory locator. 












## [readLocalFiles](../jquery/jszip/dist/jszip.js#L3626)

Read the local files, based on the offset read in the central part. 












## [readCentralDir](../jquery/jszip/dist/jszip.js#L3640)

Read the central directory. 












## [readEndOfCentral](../jquery/jszip/dist/jszip.js#L3667)

Read the end of central directory. 












## [load](../jquery/jszip/dist/jszip.js#L3763)

Read a zip file and create ZipEntries. 




|Parameter Name|Description|
|-----|-----|
|||








## [findCompression](../jquery/jszip/dist/jszip.js#L3790)

**Type:** `var`

Find a compression registered in JSZip. 




|Parameter Name|Description|
|-----|-----|
||


**Returned Value:** 








## [ZipEntry](../jquery/jszip/dist/jszip.js#L3808)

**Type:** `function`

An entry in the zip file. 




|Parameter Name|Description|
|-----|-----|
||
||








## [isEncrypted](../jquery/jszip/dist/jszip.js#L3819)

say if the file is encrypted. 





**Returned Value:**  








## [useUTF8](../jquery/jszip/dist/jszip.js#L3827)

say if the file has utf-8 filename/comment. 





**Returned Value:**  








## [readLocalPart](../jquery/jszip/dist/jszip.js#L3835)

Read the local part of a zip file and add the info in this object. 




|Parameter Name|Description|
|-----|-----|
|||








## [readCentralPart](../jquery/jszip/dist/jszip.js#L3876)

Read the central part of a zip file and add the info in this object. 




|Parameter Name|Description|
|-----|-----|
|||








## [processAttributes](../jquery/jszip/dist/jszip.js#L3909)

Parse the external file attributes and get the unix/dos permissions. 












## [parseZIP64ExtraField](../jquery/jszip/dist/jszip.js#L3938)

Parse the ZIP64 extra field and merge the info in the current ZipEntry. 




|Parameter Name|Description|
|-----|-----|
|||








## [readExtraFields](../jquery/jszip/dist/jszip.js#L3966)

Read the central part of a zip file and add the info in this object. 




|Parameter Name|Description|
|-----|-----|
|||








## [handleUTF8](../jquery/jszip/dist/jszip.js#L3992)

Apply an UTF8 transformation if needed. 












## [findExtraFieldUnicodePath](../jquery/jszip/dist/jszip.js#L4021)

Find the unicode path declared in the extra field, if any. 





**Returned Value:**  








## [findExtraFieldUnicodeComment](../jquery/jszip/dist/jszip.js#L4045)

Find the unicode comment declared in the extra field, if any. 





**Returned Value:**  








## [ZipObject](../jquery/jszip/dist/jszip.js#L4080)

**Type:** `var`

A simple object representing a file in the zip file. 




|Parameter Name|Description|
|-----|-----|
||
||
||








## [internalStream](../jquery/jszip/dist/jszip.js#L4105)

Create an internal stream for the content of this object. 




|Parameter Name|Description|
|-----|-----|
||


**Returned Value:** StreamHelper the stream.  








## [async](../jquery/jszip/dist/jszip.js#L4139)

Prepare the content in the asked type. 




|Parameter Name|Description|
|-----|-----|
||
||


**Returned Value:** Promise the promise of the result.  








## [nodeStream](../jquery/jszip/dist/jszip.js#L4149)

Prepare the content as a nodejs stream. 




|Parameter Name|Description|
|-----|-----|
||
||


**Returned Value:** Stream the stream.  








## [](../jquery/jszip/dist/jszip.js#L4159)

Return a worker for the compressed content. 




|Parameter Name|Description|
|-----|-----|
||
||


**Returned Value:** Worker the worker.  








## [](../jquery/jszip/dist/jszip.js#L4180)

Return a worker for the decompressed content. 





**Returned Value:** Worker the worker.  








## [](../jquery/jszip/dist/jszip.js#L4601)

class Deflate 

Generic JS-style wrapper for zlib calls. If you don't need 
streaming behaviour - use more simple functions: [[deflate]], 
[[deflateRaw]] and [[gzip]]. 
/ 

/internal 
Deflate.chunks -> Array 

Chunks of output data, if [[Deflate#onData]] not overriden. 
/ 

/ 
Deflate.result -> Uint8Array|Array 

Compressed result, generated by default [[Deflate#onData]] 
and [[Deflate#onEnd]] handlers. Filled after you push last chunk 
(call [[Deflate#push]] with `Z_FINISH` / `true` param) or if you 
push a chunk with explicit flush (call [[Deflate#push]] with 
`Z_SYNC_FLUSH` param). 
/ 

/ 
Deflate.err -> Number 

Error code after deflate finished. 0 (Z_OK) on success. 
You will not need it in real life, because deflate errors 
are possible only on wrong options or bad `onData` / `onEnd` 
custom handlers. 
/ 

/ 
Deflate.msg -> String 

Error message, if [[Deflate.err]] != 0 
/ 


/ 
new Deflate(options) 
- options (Object): zlib deflate options. 

Creates new deflator instance with specified params. Throws exception 
on bad params. Supported options: 

- `level` 
- `windowBits` 
- `memLevel` 
- `strategy` 
- `dictionary` 

[http://zlib.net/manual.html#Advanced](http://zlib.net/manual.html#Advanced) 
for more information on these. 

Additional options, for internal needs: 

- `chunkSize` - size of generated data chunks (16K by default) 
- `raw` (Boolean) - do raw deflate 
- `gzip` (Boolean) - create gzip wrapper 
- `to` (String) - if equal to 'string', then result will be "binary string" 
(each char code [0..255]) 
- `header` (Object) - custom header for gzip 
- `text` (Boolean) - true if compressed data believed to be text 
- `time` (Number) - modification time, unix timestamp 
- `os` (Number) - operation system code 
- `extra` (Array) - array of bytes with extra data (max 65536) 
- `name` (String) - file name (binary string) 
- `comment` (String) - comment (binary string) 
- `hcrc` (Boolean) - true if header crc should be added 

##### Example: 

```javascript 
var pako = require('pako') 
, chunk1 = Uint8Array([1,2,3,4,5,6,7,8,9]) 
, chunk2 = Uint8Array([10,11,12,13,14,15,16,17,18,19]); 

var deflate = new pako.Deflate({ level: 3}); 

deflate.push(chunk1, false); 
deflate.push(chunk2, true); // true -> last chunk 

if (deflate.err) { throw new Error(deflate.err); } 

console.log(deflate.result); 
``` 
/ 
function Deflate(options) { 
if (!(this instanceof Deflate)) return new Deflate(options); 

this.options = utils.assign({ 
level: Z_DEFAULT_COMPRESSION, 
method: Z_DEFLATED, 
chunkSize: 16384, 
windowBits: 15, 
memLevel: 8, 
strategy: Z_DEFAULT_STRATEGY, 
to: '' 
}, options || {}); 

var opt = this.options; 

if (opt.raw && (opt.windowBits > 0)) { 
opt.windowBits = -opt.windowBits; 
} 

else if (opt.gzip && (opt.windowBits > 0) && (opt.windowBits < 16)) { 
opt.windowBits += 16; 
} 

this.err = 0; // error code, if happens (0 = Z_OK) 
this.msg = ''; // error message 
this.ended = false; // used to avoid multiple onEnd() calls 
this.chunks = []; // chunks of compressed data 

this.strm = new ZStream(); 
this.strm.avail_out = 0; 

var status = zlib_deflate.deflateInit2( 
this.strm, 
opt.level, 
opt.method, 
opt.windowBits, 
opt.memLevel, 
opt.strategy 
); 

if (status !== Z_OK) { 
throw new Error(msg[status]); 
} 

if (opt.header) { 
zlib_deflate.deflateSetHeader(this.strm, opt.header); 
} 

if (opt.dictionary) { 
var dict; 
// Convert data if needed 
if (typeof opt.dictionary === 'string') { 
// If we need to compress text, change encoding to utf8. 
dict = strings.string2buf(opt.dictionary); 
} else if (toString.call(opt.dictionary) === '[object ArrayBuffer]') { 
dict = new Uint8Array(opt.dictionary); 
} else { 
dict = opt.dictionary; 
} 

status = zlib_deflate.deflateSetDictionary(this.strm, dict); 

if (status !== Z_OK) { 
throw new Error(msg[status]); 
} 

this._dict_set = true; 
} 
} 

/ 
Deflate#push(data[, mode]) -> Boolean 
- data (Uint8Array|Array|ArrayBuffer|String): input data. Strings will be 
converted to utf8 byte sequence. 
- mode (Number|Boolean): 0..6 for corresponding Z_NO_FLUSH..Z_TREE modes. 
See constants. Skipped or `false` means Z_NO_FLUSH, `true` meansh Z_FINISH. 

Sends input data to deflate pipe, generating [[Deflate#onData]] calls with 
new compressed chunks. Returns `true` on success. The last data block must have 
mode Z_FINISH (or `true`). That will flush internal pending buffers and call 
[[Deflate#onEnd]]. For interim explicit flushes (without ending the stream) you 
can use mode Z_SYNC_FLUSH, keeping the compression context. 

On fail call [[Deflate#onEnd]] with error code and return false. 

We strongly recommend to use `Uint8Array` on input for best speed (output 
array format is detected automatically). Also, don't skip last param and always 
use the same type in your code (boolean or number). That will improve JS speed. 

For regular `Array`-s make sure all elements are [0..255]. 

##### Example 

```javascript 
push(chunk, false); // push one of data chunks 
... 
push(chunk, true); // push last chunk 
``` 
/ 
Deflate.prototype.push = function (data, mode) { 
var strm = this.strm; 
var chunkSize = this.options.chunkSize; 
var status, _mode; 

if (this.ended) { return false; } 

_mode = (mode === ~~mode) ? mode : ((mode === true) ? Z_FINISH : Z_NO_FLUSH); 

// Convert data if needed 
if (typeof data === 'string') { 
// If we need to compress text, change encoding to utf8. 
strm.input = strings.string2buf(data); 
} else if (toString.call(data) === '[object ArrayBuffer]') { 
strm.input = new Uint8Array(data); 
} else { 
strm.input = data; 
} 

strm.next_in = 0; 
strm.avail_in = strm.input.length; 

do { 
if (strm.avail_out === 0) { 
strm.output = new utils.Buf8(chunkSize); 
strm.next_out = 0; 
strm.avail_out = chunkSize; 
} 
status = zlib_deflate.deflate(strm, _mode); /no bad return value 











## [](../jquery/jszip/dist/jszip.js#L4852)

Deflate#onData(chunk) -> Void 
- chunk (Uint8Array|Array|String): ouput data. Type of array depends 
on js engine support. When string output requested, each chunk 
will be string. 

By default, stores data blocks in `chunks[]` property and glue 
those in `onEnd`. Override this handler, if you need another behaviour. 
/ 
Deflate.prototype.onData = function (chunk) { 
this.chunks.push(chunk); 
}; 


/ 
Deflate#onEnd(status) -> Void 
- status (Number): deflate status. 0 (Z_OK) on success, 
other if not. 

Called once after you tell deflate that the input stream is 
complete (Z_FINISH) or should be flushed (Z_SYNC_FLUSH) 
or if an error happened. By default - join collected chunks, 
free memory and fill `results` / `err` properties. 
/ 
Deflate.prototype.onEnd = function (status) { 
// On success - join 
if (status === Z_OK) { 
if (this.options.to === 'string') { 
this.result = this.chunks.join(''); 
} else { 
this.result = utils.flattenChunks(this.chunks); 
} 
} 
this.chunks = []; 
this.err = status; 
this.msg = this.strm.msg; 
}; 


/ 
deflate(data[, options]) -> Uint8Array|Array|String 
- data (Uint8Array|Array|String): input data to compress. 
- options (Object): zlib deflate options. 

Compress `data` with deflate algorithm and `options`. 

Supported options are: 

- level 
- windowBits 
- memLevel 
- strategy 
- dictionary 

[http://zlib.net/manual.html#Advanced](http://zlib.net/manual.html#Advanced) 
for more information on these. 

Sugar (options): 

- `raw` (Boolean) - say that we work with raw stream, if you don't wish to specify 
negative windowBits implicitly. 
- `to` (String) - if equal to 'string', then result will be "binary string" 
(each char code [0..255]) 

##### Example: 

```javascript 
var pako = require('pako') 
, data = Uint8Array([1,2,3,4,5,6,7,8,9]); 

console.log(pako.deflate(data)); 
``` 
/ 
function deflate(input, options) { 
var deflator = new Deflate(options); 

deflator.push(input, true); 

// That will never happens, if you don't cheat with options :) 
if (deflator.err) { throw deflator.msg || msg[deflator.err]; } 

return deflator.result; 
} 


/ 
deflateRaw(data[, options]) -> Uint8Array|Array|String 
- data (Uint8Array|Array|String): input data to compress. 
- options (Object): zlib deflate options. 

The same as [[deflate]], but creates raw data, without wrapper 
(header and adler32 crc). 
/ 
function deflateRaw(input, options) { 
options = options || {}; 
options.raw = true; 
return deflate(input, options); 
} 


/ 
gzip(data[, options]) -> Uint8Array|Array|String 
- data (Uint8Array|Array|String): input data to compress. 
- options (Object): zlib deflate options. 

The same as [[deflate]], but create gzip wrapper instead of 
deflate one. 
/ 
function gzip(input, options) { 
options = options || {}; 
options.gzip = true; 
return deflate(input, options); 
} 


exports.Deflate = Deflate; 
exports.deflate = deflate; 
exports.deflateRaw = deflateRaw; 
exports.gzip = gzip; 

},{"./utils/common":41,"./utils/strings":42,"./zlib/deflate":46,"./zlib/messages":51,"./zlib/zstream":53}],40:[function(require,module,exports){ 
'use strict'; 


var zlib_inflate = require('./zlib/inflate'); 
var utils = require('./utils/common'); 
var strings = require('./utils/strings'); 
var c = require('./zlib/constants'); 
var msg = require('./zlib/messages'); 
var ZStream = require('./zlib/zstream'); 
var GZheader = require('./zlib/gzheader'); 

var toString = Object.prototype.toString; 

/ 
class Inflate 

Generic JS-style wrapper for zlib calls. If you don't need 
streaming behaviour - use more simple functions: [[inflate]] 
and [[inflateRaw]]. 
/ 

/internal 
inflate.chunks -> Array 

Chunks of output data, if [[Inflate#onData]] not overriden. 
/ 

/ 
Inflate.result -> Uint8Array|Array|String 

Uncompressed result, generated by default [[Inflate#onData]] 
and [[Inflate#onEnd]] handlers. Filled after you push last chunk 
(call [[Inflate#push]] with `Z_FINISH` / `true` param) or if you 
push a chunk with explicit flush (call [[Inflate#push]] with 
`Z_SYNC_FLUSH` param). 
/ 

/ 
Inflate.err -> Number 

Error code after inflate finished. 0 (Z_OK) on success. 
Should be checked if broken data possible. 
/ 

/ 
Inflate.msg -> String 

Error message, if [[Inflate.err]] != 0 
/ 


/ 
new Inflate(options) 
- options (Object): zlib inflate options. 

Creates new inflator instance with specified params. Throws exception 
on bad params. Supported options: 

- `windowBits` 
- `dictionary` 

[http://zlib.net/manual.html#Advanced](http://zlib.net/manual.html#Advanced) 
for more information on these. 

Additional options, for internal needs: 

- `chunkSize` - size of generated data chunks (16K by default) 
- `raw` (Boolean) - do raw inflate 
- `to` (String) - if equal to 'string', then result will be converted 
from utf8 to utf16 (javascript) string. When string output requested, 
chunk length can differ from `chunkSize`, depending on content. 

By default, when no options set, autodetect deflate/gzip data format via 
wrapper header. 

##### Example: 

```javascript 
var pako = require('pako') 
, chunk1 = Uint8Array([1,2,3,4,5,6,7,8,9]) 
, chunk2 = Uint8Array([10,11,12,13,14,15,16,17,18,19]); 

var inflate = new pako.Inflate({ level: 3}); 

inflate.push(chunk1, false); 
inflate.push(chunk2, true); // true -> last chunk 

if (inflate.err) { throw new Error(inflate.err); } 

console.log(inflate.result); 
``` 
/ 
function Inflate(options) { 
if (!(this instanceof Inflate)) return new Inflate(options); 

this.options = utils.assign({ 
chunkSize: 16384, 
windowBits: 0, 
to: '' 
}, options || {}); 

var opt = this.options; 

// Force window size for `raw` data, if not set directly, 
// because we have no header for autodetect. 
if (opt.raw && (opt.windowBits >= 0) && (opt.windowBits < 16)) { 
opt.windowBits = -opt.windowBits; 
if (opt.windowBits === 0) { opt.windowBits = -15; } 
} 

// If `windowBits` not defined (and mode not raw) - set autodetect flag for gzip/deflate 
if ((opt.windowBits >= 0) && (opt.windowBits < 16) && 
!(options && options.windowBits)) { 
opt.windowBits += 32; 
} 

// Gzip header has no info about windows size, we can do autodetect only 
// for deflate. So, if window size not set, force it to max when gzip possible 
if ((opt.windowBits > 15) && (opt.windowBits < 48)) { 
// bit 3 (16) -> gzipped data 
// bit 4 (32) -> autodetect gzip/deflate 
if ((opt.windowBits & 15) === 0) { 
opt.windowBits |= 15; 
} 
} 

this.err = 0; // error code, if happens (0 = Z_OK) 
this.msg = ''; // error message 
this.ended = false; // used to avoid multiple onEnd() calls 
this.chunks = []; // chunks of compressed data 

this.strm = new ZStream(); 
this.strm.avail_out = 0; 

var status = zlib_inflate.inflateInit2( 
this.strm, 
opt.windowBits 
); 

if (status !== c.Z_OK) { 
throw new Error(msg[status]); 
} 

this.header = new GZheader(); 

zlib_inflate.inflateGetHeader(this.strm, this.header); 
} 

/ 
Inflate#push(data[, mode]) -> Boolean 
- data (Uint8Array|Array|ArrayBuffer|String): input data 
- mode (Number|Boolean): 0..6 for corresponding Z_NO_FLUSH..Z_TREE modes. 
See constants. Skipped or `false` means Z_NO_FLUSH, `true` meansh Z_FINISH. 

Sends input data to inflate pipe, generating [[Inflate#onData]] calls with 
new output chunks. Returns `true` on success. The last data block must have 
mode Z_FINISH (or `true`). That will flush internal pending buffers and call 
[[Inflate#onEnd]]. For interim explicit flushes (without ending the stream) you 
can use mode Z_SYNC_FLUSH, keeping the decompression context. 

On fail call [[Inflate#onEnd]] with error code and return false. 

We strongly recommend to use `Uint8Array` on input for best speed (output 
format is detected automatically). Also, don't skip last param and always 
use the same type in your code (boolean or number). That will improve JS speed. 

For regular `Array`-s make sure all elements are [0..255]. 

##### Example 

```javascript 
push(chunk, false); // push one of data chunks 
... 
push(chunk, true); // push last chunk 
``` 
/ 
Inflate.prototype.push = function (data, mode) { 
var strm = this.strm; 
var chunkSize = this.options.chunkSize; 
var dictionary = this.options.dictionary; 
var status, _mode; 
var next_out_utf8, tail, utf8str; 
var dict; 

// Flag to properly process Z_BUF_ERROR on testing inflate call 
// when we check that all output data was flushed. 
var allowBufError = false; 

if (this.ended) { return false; } 
_mode = (mode === ~~mode) ? mode : ((mode === true) ? c.Z_FINISH : c.Z_NO_FLUSH); 

// Convert data if needed 
if (typeof data === 'string') { 
// Only binary strings can be decompressed on practice 
strm.input = strings.binstring2buf(data); 
} else if (toString.call(data) === '[object ArrayBuffer]') { 
strm.input = new Uint8Array(data); 
} else { 
strm.input = data; 
} 

strm.next_in = 0; 
strm.avail_in = strm.input.length; 

do { 
if (strm.avail_out === 0) { 
strm.output = new utils.Buf8(chunkSize); 
strm.next_out = 0; 
strm.avail_out = chunkSize; 
} 

status = zlib_inflate.inflate(strm, c.Z_NO_FLUSH); /no bad return value 











## [buf](../jquery/jszip/dist/jszip.js#L5270)

Inflate#onData(chunk) -> Void 
- chunk (Uint8Array|Array|String): ouput data. Type of array depends 
on js engine support. When string output requested, each chunk 
will be string. 

By default, stores data blocks in `chunks[]` property and glue 
those in `onEnd`. Override this handler, if you need another behaviour. 
/ 
Inflate.prototype.onData = function (chunk) { 
this.chunks.push(chunk); 
}; 


/ 
Inflate#onEnd(status) -> Void 
- status (Number): inflate status. 0 (Z_OK) on success, 
other if not. 

Called either after you tell inflate that the input stream is 
complete (Z_FINISH) or should be flushed (Z_SYNC_FLUSH) 
or if an error happened. By default - join collected chunks, 
free memory and fill `results` / `err` properties. 
/ 
Inflate.prototype.onEnd = function (status) { 
// On success - join 
if (status === c.Z_OK) { 
if (this.options.to === 'string') { 
// Glue & convert here, until we teach pako to send 
// utf8 alligned strings to onData 
this.result = this.chunks.join(''); 
} else { 
this.result = utils.flattenChunks(this.chunks); 
} 
} 
this.chunks = []; 
this.err = status; 
this.msg = this.strm.msg; 
}; 


/ 
inflate(data[, options]) -> Uint8Array|Array|String 
- data (Uint8Array|Array|String): input data to decompress. 
- options (Object): zlib inflate options. 

Decompress `data` with inflate/ungzip and `options`. Autodetect 
format via wrapper header by default. That's why we don't provide 
separate `ungzip` method. 

Supported options are: 

- windowBits 

[http://zlib.net/manual.html#Advanced](http://zlib.net/manual.html#Advanced) 
for more information. 

Sugar (options): 

- `raw` (Boolean) - say that we work with raw stream, if you don't wish to specify 
negative windowBits implicitly. 
- `to` (String) - if equal to 'string', then result will be converted 
from utf8 to utf16 (javascript) string. When string output requested, 
chunk length can differ from `chunkSize`, depending on content. 


##### Example: 

```javascript 
var pako = require('pako') 
, input = pako.deflate([1,2,3,4,5,6,7,8,9]) 
, output; 

try { 
output = pako.inflate(input); 
} catch (err) 
console.log(err); 
} 
``` 
/ 
function inflate(input, options) { 
var inflator = new Inflate(options); 

inflator.push(input, true); 

// That will never happens, if you don't cheat with options :) 
if (inflator.err) { throw inflator.msg || msg[inflator.err]; } 

return inflator.result; 
} 


/ 
inflateRaw(data[, options]) -> Uint8Array|Array|String 
- data (Uint8Array|Array|String): input data to decompress. 
- options (Object): zlib inflate options. 

The same as [[inflate]], but creates raw data, without wrapper 
(header and adler32 crc). 
/ 
function inflateRaw(input, options) { 
options = options || {}; 
options.raw = true; 
return inflate(input, options); 
} 


/ 
ungzip(data[, options]) -> Uint8Array|Array|String 
- data (Uint8Array|Array|String): input data to decompress. 
- options (Object): zlib inflate options. 

Just shortcut to [[inflate]], because it autodetects format 
by header.content. Done for convenience. 
/ 


exports.Inflate = Inflate; 
exports.inflate = inflate; 
exports.inflateRaw = inflateRaw; 
exports.ungzip = inflate; 

},{"./utils/common":41,"./utils/strings":42,"./zlib/constants":44,"./zlib/gzheader":47,"./zlib/inflate":49,"./zlib/messages":51,"./zlib/zstream":53}],41:[function(require,module,exports){ 
'use strict'; 


var TYPED_OK = (typeof Uint8Array !== 'undefined') && 
(typeof Uint16Array !== 'undefined') && 
(typeof Int32Array !== 'undefined'); 


exports.assign = function (obj /from1, from2, from3, .../) { 
var sources = Array.prototype.slice.call(arguments, 1); 
while (sources.length) { 
var source = sources.shift(); 
if (!source) { continue; } 

if (typeof source !== 'object') { 
throw new TypeError(source + 'must be non-object'); 
} 

for (var p in source) { 
if (source.hasOwnProperty(p)) { 
obj[p] = source[p]; 
} 
} 
} 

return obj; 
}; 


// reduce buffer size, avoiding mem copy 
exports.shrinkBuf = function (buf, size) { 
if (buf.length === size) { return buf; } 
if (buf.subarray) { return buf.subarray(0, size); } 
buf.length = size; 
return buf; 
}; 


var fnTyped = { 
arraySet: function (dest, src, src_offs, len, dest_offs) { 
if (src.subarray && dest.subarray) { 
dest.set(src.subarray(src_offs, src_offs + len), dest_offs); 
return; 
} 
// Fallback to ordinary array 
for (var i = 0; i < len; i++) { 
dest[dest_offs + i] = src[src_offs + i]; 
} 
}, 
// Join array of chunks to single array. 
flattenChunks: function (chunks) { 
var i, l, len, pos, chunk, result; 

// calculate data length 
len = 0; 
for (i = 0, l = chunks.length; i < l; i++) { 
len += chunks[i].length; 
} 

// join chunks 
result = new Uint8Array(len); 
pos = 0; 
for (i = 0, l = chunks.length; i < l; i++) { 
chunk = chunks[i]; 
result.set(chunk, pos); 
pos += chunk.length; 
} 

return result; 
} 
}; 

var fnUntyped = { 
arraySet: function (dest, src, src_offs, len, dest_offs) { 
for (var i = 0; i < len; i++) { 
dest[dest_offs + i] = src[src_offs + i]; 
} 
}, 
// Join array of chunks to single array. 
flattenChunks: function (chunks) { 
return [].concat.apply([], chunks); 
} 
}; 


// Enable/Disable typed arrays use, for testing 
// 
exports.setTyped = function (on) { 
if (on) { 
exports.Buf8 = Uint8Array; 
exports.Buf16 = Uint16Array; 
exports.Buf32 = Int32Array; 
exports.assign(exports, fnTyped); 
} else { 
exports.Buf8 = Array; 
exports.Buf16 = Array; 
exports.Buf32 = Array; 
exports.assign(exports, fnUntyped); 
} 
}; 

exports.setTyped(TYPED_OK); 

},{}],42:[function(require,module,exports){ 
// String encode/decode helpers 
'use strict'; 


var utils = require('./common'); 


// Quick check if we can use fast array to bin string conversion 
// 
// - apply(Array) can fail on Android 2.2 
// - apply(Uint8Array) can fail on iOS 5.1 Safary 
// 
var STR_APPLY_OK = true; 
var STR_APPLY_UIA_OK = true; 

try { String.fromCharCode.apply(null, [ 0 ]); } catch (__) { STR_APPLY_OK = false; } 
try { String.fromCharCode.apply(null, new Uint8Array(1)); } catch (__) { STR_APPLY_UIA_OK = false; } 


// Table with utf8 lengths (calculated by first byte of sequence) 
// Note, that 5 & 6-byte values and some 4-byte values can not be represented in JS, 
// because max possible codepoint is 0x10ffff 
var _utf8len = new utils.Buf8(256); 
for (var q = 0; q < 256; q++) { 
_utf8len[q] = (q >= 252 ? 6 : q >= 248 ? 5 : q >= 240 ? 4 : q >= 224 ? 3 : q >= 192 ? 2 : 1); 
} 
_utf8len[254] = _utf8len[254] = 1; // Invalid sequence start 


// convert string to array (typed, when possible) 
exports.string2buf = function (str) { 
var buf, c, c2, m_pos, i, str_len = str.length, buf_len = 0; 

// count binary size 
for (m_pos = 0; m_pos < str_len; m_pos++) { 
c = str.charCodeAt(m_pos); 
if ((c & 0xfc00) === 0xd800 && (m_pos + 1 < str_len)) { 
c2 = str.charCodeAt(m_pos + 1); 
if ((c2 & 0xfc00) === 0xdc00) { 
c = 0x10000 + ((c - 0xd800) << 10) + (c2 - 0xdc00); 
m_pos++; 
} 
} 
buf_len += c < 0x80 ? 1 : c < 0x800 ? 2 : c < 0x10000 ? 3 : 4; 
} 

// allocate buffer 
buf = new utils.Buf8(buf_len); 

// convert 
for (i = 0, m_pos = 0; i < buf_len; m_pos++) { 
c = str.charCodeAt(m_pos); 
if ((c & 0xfc00) === 0xd800 && (m_pos + 1 < str_len)) { 
c2 = str.charCodeAt(m_pos + 1); 
if ((c2 & 0xfc00) === 0xdc00) { 
c = 0x10000 + ((c - 0xd800) << 10) + (c2 - 0xdc00); 
m_pos++; 
} 
} 
if (c < 0x80) { 
/one byte 











