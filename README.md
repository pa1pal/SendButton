# SendButton
A custom send button for chatting application. 

![Phone Screenshot](https://raw.githubusercontent.com/pa1pal/SendButton/master/app/phone_gif.gif)
![Dribble GIF](https://raw.githubusercontent.com/pa1pal/SendButton/master/app/dribbble_gif.gif)

This repository is the demo of the original idea by Kirill Semushin on Dribbble : https://dribbble.com/shots/2446891-Send-Button 

#Demo
Demo apk is available in [Releases](https://github.com/pa1pal/SendButton/releases)

# Usage
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

The MIT License (MIT)
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
