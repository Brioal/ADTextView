# ADTextView
#仿京东首页垂直跑马灯组件
##演示动画:
![](https://github.com/Brioal/ADTextView/blob/master/art/1.gif)
##使用步骤:
###1.xml布局添加以下内容:
```
    <com.brioal.adtextviewlib.view.ADTextView
        android:id="@+id/ad_textview"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:gravity="center"
      />

```
###2.方法及xml属性参照
方法|xml属性|功能
:--|:--|:--
`void setSpeed(int speed)`|`ad_text_view_speed`|文字移动的速度
`void setInterval(int mInterval)`|`ad_text_view_interval`|文字停留在中心的时间
`void setFrontColor(int mFrontColor)`|`ad_text_front_color`|前缀文字颜色
`void setBackColor(int mBackColor)`|`ad_text_content_color`|内容文字颜色
`void setFrontTextSize(int frontTextSize)`|`ad_text_front_size`|前缀文字大小
`void setContentTextSize(int contentTextSize)`|`ad_text_content_size`|内容文字大小
`void setTexts(List<AdEntity> mTexts)`|无|设置显示的数据源
`void setOnItemClickListener(OnItemClickListener onItemClickListener)`|无|设置文字点击事件


#添加组件的方法
#步骤1. 项目的build.gradle的allprojects更改为如下内容:

```

	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```

#步骤二.像添加其他开源库一样添加依赖,然后sync即可使用
```
	dependencies {
	        compile 'com.github.Brioal:ADTextView:1.0'
	}
```
