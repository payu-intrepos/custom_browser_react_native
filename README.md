
# PayU CustomBrowser React-Native wrapper

## Getting started

`$ npm install com.payu.custombrowser.react-native --save`

### Mostly automatic installation

`$ react-native link com.payu.custombrowser.react-native`

### Manual installation



#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.cbwrapper.CBPackage;` to the imports at the top of the file
  - Add `new CBPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':com.payu.custombrowser.react-native'
  	project(':com.payu.custombrowser.react-native').projectDir = new File(rootProject.projectDir, 	'../node_modules/cb-wrapper/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':com.payu.custombrowser.react-native')
  	```




## Usage
```javascript
import CBPackage from 'com.payu.custombrowser.react-native';
import {NativeModules} from 'react-native';
NativeModules.CBWrapper.startPayment(params).then(response => {
        Alert.alert(response);
        }).catch(error =>   Alert.alert(error));
        }}

```
  