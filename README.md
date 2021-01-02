# Beautiful-Dialog
[![](https://jitpack.io/v/miumiuhaskeer/Beautiful-Dialog.svg)](https://jitpack.io/#miumiuhaskeer/BeautifulDialog)
<br><br>
A library that allows you to create beautiful AlertDialogs. If you use custom colors with custom view, you need to define colors in your castom 
layout and then create BeautifulDialogColors class which will be one of the parameters in the constructor of BeautifulDialog class.
<br><br>
<img src="https://github.com/miumiuhaskeer/Beautiful-Dialog/blob/master/arts/1.jpg" width="432" height="337.6" />

## Download
Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
Add the dependency
```gradle
dependencies {
  implementation 'com.github.miumiuhaskeer:BeautifulDialog:1.0.0'
}
```

## Usage
### Default implementation
```Java
BeautifulDialog dialog = new BeautifulDialog(context, null);

dialog.setTitle("Dialog title")
      .setPositiveButton("OK", null)
      .setNegativeButton("Cancel", null);
      .setMessage("Hello World!")
      .show();
```
The result is:
<br><br>
<img src="https://github.com/miumiuhaskeer/Beautiful-Dialog/blob/master/arts/4.jpg" width="432" height="203.2" />

### Custom implementation
You can create custom layout and add it to BeautifulDialog
```Java
View customView = LayoutInflater.from(context).inflate(R.layout.dialog_to_add, null);
BeautifulDialog dialog = new BeautifulDialog(context, customView);

dialog.setTitle("Dialog title")
      .setPositiveButton("OK", null)
      .setNegativeButton("Cancel", null);
      .show();
```
The result is:
<br><br>
<img src="https://github.com/miumiuhaskeer/Beautiful-Dialog/blob/master/arts/2.jpg" width="432" height="524.8" />

You can create dialog without any buttons (positive and negative) and title
```Java
View view = LayoutInflater.from(context).inflate(R.layout.dialog_to_add, null);
BeautifulDialog dialog = new BeautifulDialog(context, view);
dialog.show();
```
The result is:
<br><br>
<img src="https://github.com/miumiuhaskeer/Beautiful-Dialog/blob/master/arts/5.jpg" width="432" height="421.2" />

## License
```
MIT License

Copyright (c) 2021 miumiuhaskeer

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
