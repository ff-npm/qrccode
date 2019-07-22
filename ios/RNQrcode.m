
#import "RNQrcode.h"
#import "DYQRCodeAbility.h"
@implementation RNQrcode

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE(QRCodeModule)

#pragma mark -  原生扫描controller
RCT_EXPORT_METHOD(nativeQRCodeWithCallback:(RCTResponseSenderBlock)callback){
    dispatch_async(dispatch_get_main_queue(), ^{
        DYQRCodeNativeViewController *qrcVC = [DYQRCodeNativeViewController new];
        qrcVC.scanQRCodeResultBlock = ^(NSDictionary * _Nonnull result) {
            callback(@[result]);
        };
        if([DYQRCodeLogicHelper findCurrentViewController].navigationController.viewControllers.count != 0) {
            [[DYQRCodeLogicHelper findCurrentViewController].navigationController pushViewController:qrcVC animated:YES];
        }else {
            [[DYQRCodeLogicHelper findCurrentViewController]presentViewController:qrcVC animated:YES completion:nil];
        }
    });
}


#pragma mark -  生成二维码
RCT_EXPORT_METHOD(creatQRCodeWithAnyTextAndConfig:(NSString *)text config:(NSDictionary *)config callback:(RCTResponseSenderBlock)callback){
    dispatch_async(dispatch_get_main_queue(), ^{
        NSString *logo = [config objectForKey:@"logo"];
        CGFloat size = [[config objectForKey:@"size"] floatValue];
        NSString *codeColor = [config objectForKey:@"codeColor"];
        NSString *backgroundColor = [config objectForKey:@"backgroundColor"];
        callback(@[[[DYQRCodeAbility shareAbility]creatQRCodeWithAnyTextAndConfig:text logo:logo size:size codeColor:codeColor backgroundColor:backgroundColor]]);
    });
}

#pragma mark -  图片提取二维码信息
RCT_EXPORT_METHOD(imageQRCodeWithFilePath:(NSString *)filePath callback:(RCTResponseSenderBlock)callback){
    dispatch_async(dispatch_get_main_queue(), ^{
        callback(@[[[DYQRCodeAbility shareAbility] imageQRCodeWithFilePath:filePath]]);
    });
}

#pragma mark -  相册提取二维码信息
RCT_EXPORT_METHOD(libraryPhotoQRCodeWithCallback:(RCTResponseSenderBlock)callback){
    dispatch_async(dispatch_get_main_queue(), ^{
        DYQRCodeAbility *ability = [DYQRCodeAbility shareAbility];
        dispatch_async(dispatch_get_main_queue(), ^{
            BOOL hasAuth = [ability libraryPhotoQRCode];
            if (hasAuth == YES) {
                ability.abilityScanQRCodeBlock = ^(NSDictionary * _Nonnull result) {
                    callback(@[result]);
                };
            }else {
                callback(@[@{@"code":@"201",@"msg":@"no Auth",@"resp":@""}]);
            }
            
        });
        
    });
}

#pragma mark - 闪光灯开合(UI使用)
RCT_EXPORT_METHOD(flashSwitch:(int)open){
    dispatch_async(dispatch_get_main_queue(), ^{
        DYQRCodeAbility *ability = [DYQRCodeAbility shareAbility];
        [ability flashSwitch:open];
    });
}

#pragma mark - 跳转权限
RCT_EXPORT_METHOD(authJump){
    dispatch_async(dispatch_get_main_queue(), ^{
        DYQRCodeAbility *ability = [DYQRCodeAbility shareAbility];
        [ability authJump];
    });
}

@end
  
