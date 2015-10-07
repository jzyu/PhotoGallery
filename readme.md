# 网络图片浏览器

## 特性 ##
1.优先加载大图，如大图尚未cache则加载小图：原图->大图->小图
2.点击2次放大，再点恢复原始大小(PhotoView控件特性)

## 实现方式 ##
1.用ViewPager包裹PhotoView控件(extend of ImageView)
2.图片加载库：Glide
3.图片存储：qiniu


图片类型：(SCREEN_WIDTH = 600)
 小图: 宽度参数 < SCREEN_WIDTH
 大图: 宽度参数 >= SCREEN_WIDTH
 原图: 宽带参数 = None

# TODO
1. 以下条件按顺序判断
   1) 原图有cache：显示原图
   2) 大图有cache：显示大图
   3) 小图有cache：显示小图，原图button位置显示加载中动画
   4) 否则：图片中心位置显示加载中动画直到小图ready
2. 大图ready时显示原图button，点击后隐藏button显示加载中动画
3. 平滑视觉效果：初次加载时显示从原始位置过度和放大特效
4. adjust UI layout:
        cur/total
     原图         保存
