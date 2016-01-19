# SendButton
[![Build Status](https://travis-ci.org/pa1pal/SendButton.svg?branch=master)](https://travis-ci.org/pa1pal/SendButton)   [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-SendButton-green.svg?style=true)](https://android-arsenal.com/details/1/3039)

Send Button Custom View for chatting application. 
The custom view developed by drawing path over the coordinates of the figure. And the animation done by continously changing the coordinates in one direction.

![Phone Screenshot](https://raw.githubusercontent.com/pa1pal/SendButton/master/app/phone_gif.gif)
![Dribble GIF](https://raw.githubusercontent.com/pa1pal/SendButton/master/app/dribbble_gif.gif)

This repository is the demo of the original idea by Kirill Semushin on Dribbble : https://dribbble.com/shots/2446891-Send-Button 

# Demo
Demo apk is available in [Releases](https://github.com/pa1pal/SendButton/releases)

# Usage

Add dependency 
```gradle
dependencies {
    compile 'pa1pal.sendbutton.lib:lib:1.0'
}
```
```xml
xmlns:app="http://schemas.android.com/apk/res-auto"
```

```xml
<pa1pal.sendbutton.SendButton
        android:id="@+id/send_button"
        android:layout_height="wrap_content"
        android:layout_width="wrap_content"
        app:buttonColor="@color/white"
        app:planeColor="@color/orange"
        app:buttonSide="200dp"
        app:borderStrokeWidth="5"
        app:planeStrokeWidth="5"
        />
```

# Customization
## Attributes
```xml
* Button Color          format="color" 
* Plane Color           format="color"
* ButtonSide            format="dimension"
* Border Stroke Width   format="integer"
* Plane Stroke Width    format="integer"
* Animation type        format="enum"
* Duration              format="integer"
```
### Examples
![](https://raw.githubusercontent.com/pa1pal/SendButton/master/app/Screenshots/sb1.png)
![](https://raw.githubusercontent.com/pa1pal/SendButton/master/app/Screenshots/sb2.png)
![](https://raw.githubusercontent.com/pa1pal/SendButton/master/app/Screenshots/sb3.png)
![](https://raw.githubusercontent.com/pa1pal/SendButton/master/app/Screenshots/sb4.png)

The MIT License
===============

Copyright (c) [2016] [Pawan Pal, Devesh Khandelwal]

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
