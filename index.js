import QrcodeView from "./QrcodeView";
import { NativeModules } from 'react-native';
const { RNQRCode,DYAVCaptureVideoPreviewView } = NativeModules;
RNQRCode.initQrCodeView = DYAVCaptureVideoPreviewView.QrCodeView;
export default RNQRCode;
module.exports = {
    QrcodeView,
    RNQRCode,
}