
# react-native-qrcode

## Getting started

`$ npm install react-native-qrcode --save`

### Mostly automatic installation

`$ react-native link react-native-qrcode`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-qrcode` and add `RNQrcode.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNQrcode.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNQrcodePackage;` to the imports at the top of the file
  - Add `new RNQrcodePackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-qrcode'
  	project(':react-native-qrcode').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-qrcode/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-qrcode')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNQrcode.sln` in `node_modules/react-native-qrcode/windows/RNQrcode.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Qrcode.RNQrcode;` to the usings at the top of the file
  - Add `new RNQrcodePackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNQrcode from 'react-native-qrcode';

// TODO: What to do with the module?
RNQrcode;
```
  