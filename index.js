import QrcodeView from "./QrcodeView";
import { NativeModules } from 'react-native';
const { RNQRCode, QrCodeView } = NativeModules;
RNQRCode.initQrCodeView = QrCodeView.initScanQRCodeWithCallback;
export default RNQRCode;
module.exports = {
    QrcodeView,
    RNQRCode,
}