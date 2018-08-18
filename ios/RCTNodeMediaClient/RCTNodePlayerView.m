//
//  RCTNodePlayerView.m
// 
//
//  Created by Mingliang Chen on 2017/11/29.
//  Copyright © 2017年 NodeMedia. All rights reserved.
//

#import "RCTNodeMediaClient.h"
#import "RCTNodePlayerView.h"
#import <NodeMediaClient/NodeMediaClient.h>


@interface RCTNodePlayerView()

@property (strong,nonatomic) NodePlayer *np;

@end

@implementation RCTNodePlayerView

- (id)init {
  self = [super init];
  if(self) {
    _np = [[NodePlayer alloc] initWithPremium:[RCTNodeMediaClient premium]];
    [_np setPlayerView:self];
    _autoplay = NO;
    _inputUrl = nil;
  }
  return self;
}

- (void)setInputUrl:(NSString *)inputUrl {
  _inputUrl = inputUrl;
  [_np setInputUrl:inputUrl];
  if(_autoplay) {
    [_np start];
  }
}

-(void)setBufferTime:(int)bufferTime {
  _bufferTime = bufferTime;
  [_np setBufferTime:bufferTime];
}

-(void)setMaxBufferTime:(int)maxBufferTime {
  _maxBufferTime = maxBufferTime;
  [_np setMaxBufferTime:maxBufferTime];
}

-(void)setScaleMode:(NSString *)scaleMode {
  int mode = UIViewContentModeScaleToFill;
  if([scaleMode isEqualToString:@"ScaleAspectFit"]) {
    mode = UIViewContentModeScaleAspectFit;
  } else if([scaleMode isEqualToString:@"ScaleAspectFill"]) {
    mode = UIViewContentModeScaleAspectFill;
  }
  [_np setContentMode:mode];
  
}

- (void)setAutoplay:(BOOL)autoplay {
  _autoplay = autoplay;
  if(_inputUrl) {
    [_np start];
  }
}

-(int)start {
  return [_np start];
}

-(int)stop {
  return [_np stop];
}

-(int)pause {
  return [_np pause];
}
@end
