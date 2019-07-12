
import React, {Component} from 'react';
import ReactNative from 'react-native';
import PropTypes from 'prop-types';
import {ViewPropTypes} from 'react-native';

// var {
//   Component,
// } = React;

let {
  requireNativeComponent,
} = ReactNative;

let FFQrcodeView = requireNativeComponent('DYAVCaptureVideoPreviewView', QrcodeView);

export default class QrcodeView extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <FFQrcodeView 
                {...this.props}                 
            />);
    }
}

// JMRTCView.propTypes = {
//     username: PropTypes.string.isRequired
// }

