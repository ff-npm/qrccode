import QrcodeView from "./QrcodeView";
import { NativeModules } from 'react-native';

const { QRCodeModule } = NativeModules;

export default QRCodeModule;
module.exports = {
    QrcodeView,
    QRCodeModule
}