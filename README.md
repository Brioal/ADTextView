
# 使用此组件的方法
# 步骤1. 项目的build.gradle的allprojects更改为如下内容:

```

	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```

# 步骤二.像添加其他开源库一样添加依赖,然后sync即可使用[![](https://jitpack.io/v/Brioal/ADTextView.svg)](https://jitpack.io/#Brioal/ADTextView)
```
	dependencies {
	        compile 'com.github.Brioal:ADTextView:1.2'
	}
```
## 效果图:
![](https://github.com/Brioal/ADTextView/blob/master/art/1.gif)
## 使用步骤:
### 1.xml组件
```
    <com.brioal.adtextviewlib.view.ADTextView
        android:id="@+id/ad_textview"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:background="@color/colorPrimary"
        android:gravity="center"
        android:padding="10dp"
        />
```
#### 在xml布局内可以设置背景颜色,padding等其他一些View的基本属性
### 2.代码设置
```
mADTextView = findViewById(R.id.ad_textview);
        final List<String> texts = new ArrayList<>();
        texts.add("11 111111111111111");
        texts.add("22 2222222222222222");
        texts.add("33 3333333333333333");
        texts.add("44 44444444444444444444");
        mADTextView.setInterval(2000);
        mADTextView.init(texts,new OnAdChangeListener() {
            @Override
            public void DiyTextView(TextView textView) {
                textView.setTextSize(20);
                textView.setTextColor(Color.WHITE);
                SpannableStringBuilder builder = new SpannableStringBuilder(textView.getText());
                builder.setSpan(new ForegroundColorSpan(Color.RED),0,2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                textView.setText(builder);
            }

        });
```
### 核心方法是`.init(List<string> list , OnAdChangeListener listener);`,传入的List即为数据源,组件将会滚动显示传入的数据,另外可以设置间隔时间和进入退出的动画,另外在`OnAdChangeListener`的回掉里面可以对TextView进行进一步的定制,在回掉里卖弄修改的内容会被实时的显示上去
### 提供的主要方法列表

---- | ----
` setInterval(int interval)`|设置间隔时间
`setAnimationIn(int animationIn)`|设置进入动画
`setAnimationOut(int animationOut)`|设置退出动画
`init(final List<String> texts, OnAdChangeListener listener)`|显示内容

#### 注:必须调用init方法,否则不显示内容

