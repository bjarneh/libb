
### small java library


```java
// dynamic replace (sort of like $1)
String unbold = handy.dynReplace("<b>([^<]+)<\\/b>", boldXHTML);

// file slurping with UTF-8
String content = new String(io.raw("file.txt"), "UTF-8");

// file slurping stdin
String content = new String(io.raw(System.in));

// file slurping over http
String content = new String(io.wget("http://example.html"));
```


 - [javadoc][1]


[1]: http://bjarneh.github.com/libb/htm/index.html "libb doc"
