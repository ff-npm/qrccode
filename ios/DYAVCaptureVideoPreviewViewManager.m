//
//  DYAVCaptureVideoPreviewViewManager.m
//  QRCodeForNPM
//
//  Created by ff on 2019/6/28.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import "DYAVCaptureVideoPreviewViewManager.h"
#import <React/RCTBridge.h>
#import <React/RCTEventDispatcher.h>
#import "DYQRCode.framework/Headers/DYQRCodeAbility.h"

@interface DYAVCaptureVideoPreviewViewManager ()

@property (nonatomic, strong)DYPreviewView *previewView;

@end

@implementation DYAVCaptureVideoPreviewViewManager

RCT_EXPORT_MODULE(DYAVCaptureVideoPreviewView)

RCT_EXPORT_VIEW_PROPERTY(scanImageName, NSString);
RCT_EXPORT_VIEW_PROPERTY(borderColor, UIColor);
RCT_EXPORT_VIEW_PROPERTY(cornerColor, UIColor);
RCT_EXPORT_VIEW_PROPERTY(cornerWidth, CGFloat);
RCT_EXPORT_VIEW_PROPERTY(backgroundAlpha, CGFloat);
RCT_EXPORT_VIEW_PROPERTY(animationTimeInterval, NSTimeInterval);


- (UIView *)view {
  if (!self.previewView) {
    self.previewView = [DYPreviewView new];
  }
  return self.previewView;
}

#pragma mark - 初始化扫描对象(UI使用,已开启扫描)
RCT_EXPORT_METHOD(initScanQRCodeWithCallback:(RCTResponseSenderBlock)callback){
  dispatch_async(dispatch_get_main_queue(), ^{
    if ([DYQRCodeLogicHelper cameraAuth] == YES) {
      dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(0.5 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
        DYQRCodeAbility *ability = [DYQRCodeAbility shareAbility];
        [ability initScanQRCodeWithSuperView:self.view];
        ability.abilityScanQRCodeBlock = ^(NSString * _Nonnull result) {
          callback(@[result]);
        };
      });
    }else {
      NSLog(@"没有权限");
    }
  });
}

#pragma mark - 开启/关闭扫描
RCT_EXPORT_METHOD(scanMessionSwitch:(bool)open){
  dispatch_async(dispatch_get_main_queue(), ^{
    DYQRCodeAbility *ability = [DYQRCodeAbility shareAbility];
    [ability scanMessionSwitch:open];
    [self->_previewView handleAnimation:open];
  });
}

@end
