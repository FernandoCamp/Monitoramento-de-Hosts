package br.com.fernando.ping;

import java.io.IOException;
import java.net.UnknownHostException;

public class WrapperException extends RuntimeException {
    final UnknownHostException uhe;
    final IOException ioe;

    WrapperException(UnknownHostException uhe) {
      super(uhe);
      this.uhe = uhe;
      this.ioe = null;
    }

    WrapperException(IOException ioe) {
      super(ioe);
      this.uhe = null;
      this.ioe = ioe;
    }

    public void throwWrappedException() throws UnknownHostException, IOException {
      if (this.uhe != null) throw uhe;
      if (this.ioe != null) throw ioe;
    }
}
