import { NativeModules } from 'react-native';
import Base from './Base';

const FingerprintProNativeModule = NativeModules.FingerprintPro

export default class FingerprintPro extends Base {
  constructor() {
    super();
  }

  /**
   * Get unique visitor id.
   */
  public getVisitorId = (): Promise<string> => {
    this.debugLog('call native module');
    return FingerprintProNativeModule.getVisitorId()
      .then((visitorId: string) => {
        return visitorId
      })
      .catch((err: any) => {
        this.debugLog(err);
        this.throwError(err, 'getVisitorId');
      });
  };
}
