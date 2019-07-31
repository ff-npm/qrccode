
# react-native-ff-qrcode

## Getting started

`$ npm install react-native-ff-qrcode --save`

### Mostly automatic installation

`$ react-native link react-native-ff-qrcode`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-ff-qrcode` and add `RNQrcode.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNQrcode.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainApplication.java`
  - Add `import com.ffomall.rns.qrcode.RNQrcodePackage;` to the imports at the top of the file
  - Add `new RNQrcodePackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-ff-qrcode'
  	project(':react-native-ff-qrcode').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-ff-qrcode/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-ff-qrcode')
  	```


## Usage
```javascript
import {RNQrcode,QrcodeView} from 'react-native-ff-qrcode';

// TODO: What to do with the module?
RNQrcode;
```
## 方法 映射模块名称 RNQRCode
### 1. nativeQRCodeWithCallback
	描述：完整扫码功能不需要js参与
	参数 callback 返回值 object 扫码结果
        {code: number, msg: string, resp: string}
        code:  200=成功,201 = 没相册权限,202=没相机权限,203=扫码无结果
        msg: 扫码结果描述（非扫码结果）
        resp：是个字符串,code=200的时候返的就是扫码结果串,不是200的时候是个空字符串
### 2. imageQRCodeWithFilePath
	描述：根据图片路径（本地/网络）识别二维码
	参数 string 图片路径
	参数 callback 返回值 object 扫码结果
        {code: number, msg: string, resp: string}
        code:  200=成功,201 = 没相册权限,202=没相机权限,203=扫码无结果
        msg: 扫码结果描述（非扫码结果）
        resp：是个字符串,code=200的时候返的就是扫码结果串,不是200的时候是个空字符串
### 3. libraryPhotoQRCodeWithCallback
	描述：打开相册选择图片识别二维码
	参数 callback 返回值 object 扫码结果
        {code: number, msg: string, resp: string}
        code:  200=成功,201 = 没相册权限,202=没相机权限,203=扫码无结果
        msg: 扫码结果描述（非扫码结果）
        resp：是个字符串,code=200的时候返的就是扫码结果串,不是200的时候是个空字符串
### 4. flashSwitch
	描述：关闭/打开闪光灯 配合扫码组件使用
	参数 int 0：关闭/1：打开
### 5. authJump
	描述：跳转手机应用权限设置（安卓暂无此方法）
### 6. initQrCodeView
    描述：QrcodeView组件初始化
	参数 callback 返回值 object 扫码结果
        {code: number, msg: string, resp: string}
        code:  200=成功,201 = 没相册权限,202=没相机权限,203=扫码无结果
        msg: 扫码结果描述（非扫码结果）
        resp：是个字符串,code=200的时候返的就是扫码结果串,不是200的时候是个空字符串
------------
demo： https://github.com/ff-npm/ff-rn-npm-demo