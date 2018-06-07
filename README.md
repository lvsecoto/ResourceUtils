# Resource Utils

## About

Utils for creating resource, like shape drawable

## How to use

### Adding dependency

Step 1. Add it in your root build.gradle at the end of repositories:
```gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. Add the dependency
```gradle
	dependencies {
	        implementation 'com.github.lvsecoto:resource-utils:lastVersion'
	}
```

### Enabling data binding

### Creating shape drawable like you did before

Using in Java code
``` java
ShapeDrawableUtils
            .builder(context)
            .solid(Color.White)
            .stroke(16, Color.Red)
            .corner(4)
            .startColor(Color.White)//gradient end color
            .endColor(Color.Red)    //gradient start color
            .angle(45)              //must be multiple of 45
            .build()
```

or in layout resource file
``` xml
<ImageView
    android:src="@{ShapeDrawableUtils.builder(context).solid(@color/colorPrimary).stroke(2, @color/colorAccent).corner(8).build()}" />
```
*The dimen unit default to dp*
