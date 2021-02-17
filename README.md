# AndroidBase 
> 一个借鉴[AndroidPorject](https://github.com/getActivity/AndroidProject "AndroidProject")的base的安卓封装基类。

**目前还在完善中...**

>目录

+ action  
    1.ClickAction  
    2.HandlerAction
    
+ activity  
    1.BaseActivity  
    2.BaseBindViewActivity
  
+ fragment  
    1.BaseFragment  
    2.BaseBindViewFragment  
  
+ adapter  
    1.BaseRecyclerViewAdapter  
    2.RecyclerViewBindAdapter  
    3.RecyclerViewPageAdapter

>详情

+ action   
- [x] ClickAction  
    1.点击的接口  
    2.提供总体式设置监听器  
    3.需要提供findViewById方法来获取view(自带的不需要)  

- [x] HandlerAction  
    1.Handler的接口  
    2.如果需要自定义Handler(比如设置回调),需要重写getHandler()方法。  
    2.提供Handler的一些方法。可直接调用

+ activity
- [ ] BaseActivity  
    1.Activity的基类  
    2.分解onCreate()方法  
    3.点击外部自动隐藏输入法  
    4.提供等待Dialog。
    5.简化findViewById,变成bindView,并且当绑定失败时，抛出异常。  
    6.自带TAG(类名),不需要自己定义了(主要是每次logd后,IDEA会帮我搞个TAG,所以就写了个这个。)  
    7.后续添加切换动画,startActivityForResult()等方法。

- [ ] BaseBindViewActivity  
    1.Activity的基类  
    2.大部分方法和BaseActivity一样。唯一区别在与,使用了视图绑定。子类使用**bindView**可自动获取对象。
    3.后续和BaseActivity的想法一样。
  
+ fragment  
- [ ] BaseFragment  
    1.Fragment的基类  
    2.防止getActivity()的空指针  
    3.提供等待Dialog  
    4.提供findViewById。方便绑定View
    5.后续添加动画。

- [ ] BaseBindViewFragment  
    1.Fragment的基类  
    2.与BaseFragment一样,唯一的区别是使用视图绑定，绑定的对象是**bindView**。 
    3.后续想法一样。
  
+ adapter
    >当时写这个RecyclerView的适配器主要是因为每次都要写一个类出来，然后我就想着能不能和ListView的适配器一样，简化一些。(因为有些时候，真的很简单。)于是就写了个这个，通过将OnBindViewHolder接口，将数据绑定和处理扔了出来，而自己提供了数据的操作。但是就目前而言，好像有些错误。
- [ ] BaseRecyclerViewAdapter  
    1.RecyclerView的适配器基类。   
    2.添加了一些对数据操作的方法。  
    3.然后没了。这个方法写的好像有点问题，后续要调试一下。
- [ ] RecyclerViewPageAdapter    
    1.RecyclerView的适配器.  
    2.阿巴阿巴。
  
- [ ] RecyclerViewBindAdapter    
      1.使用了视图绑定的RecyclerView的适配器.  
      2.阿巴阿巴。
  
>后语

    给自己用的。


  


  
  

