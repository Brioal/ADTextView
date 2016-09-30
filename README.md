# ADTextView
#使用此组件的方法
#步骤1. 项目的build.gradle的allprojects更改为如下内容:

```

	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```

#步骤二.像添加其他开源库一样添加依赖,然后sync即可使用[![](https://jitpack.io/v/Brioal/ADTextView.svg)](https://jitpack.io/#Brioal/ADTextView)
```
	dependencies {
	        compile 'com.github.Brioal:ADTextView:1.2'
	}
```
#仿京东首页垂直跑马灯组件
##京东客户端的轮播文字效果:
![这里写图片描述](http://img.blog.csdn.net/20160530111703822)
##本组件的演示动画:
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

##数据源实体:AdEntity结构
```
 private String mFront; //前面的文字
    private String mBack; //后面的文字
    private String mUrl;//包含的链接

    public AdEntity(String mFront, String mBack, String mUrl) {
        this.mFront = mFront;
        this.mBack = mBack;
        this.mUrl = mUrl;
    }
```
###文字点击事件接口
```
public interface OnItemClickListener {
         void onClick(String mUrl); //返回所点击的内容的链接
}
```
###使用方法:
```
 mList.add(new AdEntity("前缀1", "内容1", "连接1"));
        mList.add(new AdEntity("前缀2", "内容2", "连接2"));
        mList.add(new AdEntity("前缀3", "内容3", "连接3"));
        mList.add(new AdEntity("前缀4", "内容4", "连接4"));
        mADTextView.setFrontColor(Color.RED);
        mADTextView.setBackColor(Color.BLACK);
        mADTextView.setTexts(mList);
        mADTextView.setOnItemClickListener(new ADTextView.OnItemClickListener() {
            @Override
            public void onClick(String mUrl) {
                if (mToast == null) {
                    mToast = Toast.makeText(AdTextViewActivity.this, mUrl, Toast.LENGTH_LONG);
                } else {
                    mToast.setText(mUrl);
                }
                mToast.show();
            }
        });
```
##这样设置之后即可使用.(实际项目中List更换为要显示的数据源)
##觉得有用的可以点个star


