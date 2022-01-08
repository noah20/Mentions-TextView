# Mentions Textview

[![](https://jitpack.io/v/noah20/mentions-textview.svg)](https://jitpack.io/#noah20/mentions-textview)

Custome Textview with Mentions and hashtags being clickable. 



## Download
Step 1. Add it in your root build.gradle at the end of repositories:
may be you need to add maven to settings.gradle file
``` gradle 
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```	
Step 2. Add the dependency
``` gradle 
dependencies {
	implementation 'com.github.noah20:mentions-textview:1.0.1'
}

```

## How to use

in your xml layout add this block of code 


``` xml 
    <com.sol.textviewutils.SocialTextView
        android:id="@+id/socialTextView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="hello #world from @Home"
        android:padding="7dp"
        android:textColor="#DDD5D5"
        app:boldMentionText="true"
        app:boldHashText="true"
        app:highlightColor="#EA80FC"
        app:hashColor="#80D8FF"
        app:mentionColor="#FF8A80"
        app:tagType="hash|mention" />

```

and in activity set callback listener to handle action 

``` kotlin 
socialTextView.setSocialCallback(object :OnViewClick{
	override fun onHashClick(tag: String) {
	    // do action here 
	}

	override fun onMentionClick(mention: String) {
	    // do action here 
	}

    })
``` 

also you can update textview attr by code like
``` kotlin
with(socialTextView){
   mentionColor = android.graphics.Color.RED
   hashColor  = android.graphics.Color.YELLOW
   boldMentionText = true
   boldHashText = true
   type = SocialTextView.FLAG_HASH
}

```
## Screenshots

![App Screenshot](https://i2.paste.pics/3ef00e229665a20025c5f7add8bd202f.png?trs=0e0a9d8b27f1e5c927184a23d130a717d31fec49db2c5d8dc4a692aeab825c69)
