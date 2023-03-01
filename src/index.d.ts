declare class FingerprintPro {
  constructor(isDebug: boolean);

  public getVisitorId(): Promise<string>;
}

export default FingerprintPro;
