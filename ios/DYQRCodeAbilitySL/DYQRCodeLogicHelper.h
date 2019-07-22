//
//  DYQRCodeLogicHelper.h
//  QRCodeForNPM
//
//  Created by ff on 2019/6/27.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
NS_ASSUME_NONNULL_BEGIN

@interface DYQRCodeLogicHelper : NSObject

// 获得当前controller
+ (UIViewController *)findCurrentViewController;

// 空字符串判断
+(BOOL)isNoneString:(NSString *)str;

// 16进制字符串转颜色
+ (UIColor *)colorWithHexString:(NSString *)color ;

// 相机权限
+(BOOL)cameraAuth;

// 相册权限
+(BOOL)libraryAuth;



@end

NS_ASSUME_NONNULL_END
