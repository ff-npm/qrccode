import QrcodeView from "./QrcodeView";
import { NativeModules } from 'react-native';
const { RNQRCode, QrCodeView } = NativeModules;
RNQRCode.initQrCodeView = QrCodeView.initScanQRCodeWithCallback;
RNQRCode.flashSwitch = QrCodeView.flashSwitch;
RNQRCode.authJump = QrCodeView.authJump;
export default RNQRCode;
module.exports = {
    QrcodeView,
    RNQRCode,
}