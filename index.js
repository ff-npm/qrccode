import QrcodeView from "./QrcodeView";
import { NativeModules } from 'react-native';
const { RNQRCode, QrCodeView } = NativeModules;
RNQRCode.initQrCodeView = QrCodeView.initScanQRCodeWithCallback;
if(!RNQRCode.flashSwitch){
    RNQRCode.flashSwitch = QrCodeView.flashSwitch;
}
if(!RNQRCode.authJump){
    RNQRCode.authJump = ()=>{};
}
export default RNQRCode;
module.exports = {
    QrcodeView,
    RNQRCode,
}