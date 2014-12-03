package main.myatm;

/**
 * Created by Надя on 30.11.2014.
 */
public class NoCardExeption extends Throwable {
        @Override
        public void printStackTrace() {
            super.printStackTrace();
            System.err.println("No Card Is Inserted");
        }

}
