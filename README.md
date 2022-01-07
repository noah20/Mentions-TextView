# Mentions Textview

[![](https://jitpack.io/v/noah20/mentions-textview.svg)](https://jitpack.io/#noah20/mentions-textview)

Make Mentions and hashtags clickable in Textview

## Download
Add it in your root build.gradle at the end of repositories:
may be you need to add maven to settings.gradle file

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.noah20:mentions-textview:1.0.1'
	}
    

## How do I use Mentions TextView

in your xml layout add this block of code 



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


and in activity set callback listener to handle action 


    binding.socialTextView.setSocialCallback(object :OnViewClick{
                override fun onHashClick(tag: String) {
                    TODO("Not yet implemented")
                }

                override fun onMentionClick(mention: String) {
                    TODO("Not yet implemented")
                }

            })

also you can update textview attr by code like

            holder.binding.socialTextView.mentionColor = Color.RED
            holder.binding.socialTextView.hashColor  = Color.YELLOW
            holder.binding.socialTextView.boldMentionText = true
            holder.binding.socialTextView.boldHashText = true
            holder.binding.socialTextView.type = SocialTextView.FLAG_HASH
