//
//  RCTNodeMediaClient.m
//  RCTNodeMediaClient
//
//  Created by Mingliang Chen on 2018/2/28.
//  Copyright © 2018年 Mingliang Chen. All rights reserved.
//

#import "RCTNodeMediaClient.h"

@implementation RCTNodeMediaClient

RCT_EXPORT_MODULE(NodeMediaClient);


static NSString *_premium = @"";

+ (NSString*)premium {
    return _premium;
}

+ (void)setPremium:(NSString *)premium {
    _premium = premium;
}

RCT_EXPORT_METHOD(setPremium:(NSString *)premium)
{
    _premium = premium;
}
@end
