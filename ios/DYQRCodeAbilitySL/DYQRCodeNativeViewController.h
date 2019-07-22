//
//  DYQRCodeNativeViewController.h
//  QRCodeForNPM
//
//  Created by ff on 2019/6/27.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface DYQRCodeNativeViewController : UIViewController

@property(nonatomic,copy)void(^scanQRCodeResultBlock)(NSDictionary *result);

@end

NS_ASSUME_NONNULL_END
