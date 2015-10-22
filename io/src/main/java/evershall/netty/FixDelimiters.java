package evershall.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public final class FixDelimiters {

   public static ByteBuf[] fixFieldDelimiter() {
      return new ByteBuf[] { Unpooled.wrappedBuffer(new byte[] { (char) 1 }) };
   }

   private FixDelimiters() {
      // Unused
   }
}
