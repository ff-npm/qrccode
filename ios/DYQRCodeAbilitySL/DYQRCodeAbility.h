//
//  DYQRCodeAbility.h
//  QRCodeForNPM
//
//  Created by ff on 2019/6/27.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import <Photos/Photos.h>
#import "DYQRCodeNativeViewController.h"
#import "DYQRCodeLogicHelper.h"
#import "DYPreviewView.h"
#import "DYScanAnimationView.h"
NS_ASSUME_NONNULL_BEGIN

@interface DYQRCodeAbility : NSObject

@property(nonatomic,copy)void(^abilityScanQRCodeBlock)(NSString *result);
@property(nonatomic,copy)void(^abilityLightBrightnessBlock)(CGFloat brightness);

// 初始化
+ (instancetype)shareAbility;

// 生成二维码
-(NSString *)creatQRCodeWithAnyTextAndConfig:(NSString *)text logo:(NSString *)logo size:(CGFloat)size codeColor:(NSString *)codeColor backgroundColor:(NSString *)backgroundColor;

// 图片提取二维码信息
-(NSString *)imageQRCodeWithFilePath:(NSString *)filePath;

// 相册提取二维码信息
-(void)libraryPhotoQRCode;

// 开启扫描提取二维码信息
- (void)initScanQRCodeWithSuperView:(UIView *)superView;

// 扫描功能开关
- (void)scanMessionSwitch:(bool)open;

// 闪光灯开关
- (void)flashSwitch:(bool)open;

@end

NS_ASSUME_NONNULL_END
