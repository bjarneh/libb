
## Library for common tasks in Java

An attempt at making a (non-verbose) library for common tasks in Java.

### Example

```java
// dynamic replace (sort of like $1)
String unbold  = handy.dynReplace("<b>([^<]+)<\\/b>", boldXHTML);

// file slurping with specified encoding
String content = new String(io.raw("file.txt"), "UTF-8");

// file slurping stdin
String content = new String(io.raw(System.in));

// file slurping over http with specified encoding
String content = new String(io.wget("http://example.html"), "ISO-8859-1");

// path walking
String[] files = path.walk("/some/path");

// path walk ignoring version control files (git/hg/svn/bzr/cvs)
String[] files = path.walkVCS("/some/path");

// path walk ignoring 'tmp' directories
String[] files = path.walk("/some/path",
        new FileFilter(){
            @Override
            public boolean accept(File file){
                if(file.isDirectory()){
                    return ! file.getName().equals("tmp");
                }
                return true;
            }
        }
);
```

### [javadoc][1]


[1]: http://bjarneh.github.com/libb/htm/index.html "libb doc"
