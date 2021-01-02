
import java.util.logging.Level;
import java.util.logging.Logger;


public class SafeThread {
   
    /*
    
    bu alanda synchronized anahtar kelimesi ve threadler ile kullanılabilen join metodu uygulamalarını gerçekleştirmeye çalışıyorum.
    
    */
    private int count = 0;
    
    public void threadlericalıstır(){
        
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            for(int i = 0;i<5000;i++){
                count++;
            }
            }
        });
            Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            for(int i = 0;i<5000;i++){
                count++;
            }
            }
        });
                 thread1.start();
            thread2.start();
        try {
            thread1.join();
            thread2.join();
            //burada count sürekli farklı değerler alıyor ve standart dışı sonuçlar veriyor iki işlem içindede count++ var bunun olumsuz bir sonucu var ikiside counta erişebilir anlamına geliyor.
            //coun++ = count =count+1;bu yapıyla bellekte 3 farklı işlem yapıyoruz birinci olarak countun o anki değerini alıyoruz daha sonra o anki değerine bir ekliyoruz.Daha sonra countun güncellenmiş değerini bellekteki yerine ekliyoruz.
            //ancak bizim yapımızda thread1 ve thread2 aynı değişkene ulaştıkları için burda değer güncellenmeden o değeri güncellemeye çalışabilir..
        } catch (InterruptedException ex) {
            Logger.getLogger(SafeThread.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            //thread1.start();
            //thread2.start();
            //burdaki threadler dışındaki tüm işlemler main threadimize ait olmuş gibi davranıyor
            
            System.out.println("count değişkenimizin değeri = "+count);
            System.out.println("buradki yazdırmalarda main threade ait işlemlerdir");
           // bu yapıyla count 0 olarak karşımıza çıkıyor bunun sebebi bu threadlerin çalışmasının bitmesi beklenmeden main thread de çalıştığı için bu sonuç karşımıza çıkıyor.
           //main threadimizin çalışması için bu threadlerin çalışmasının bitmesini isteyebiliriz.Bunu join metodu ile gerçekleştirebiliriz.
           
    }
    public static void main(String[] args) {
        /*
        
        main metodumuzu static bir metod ve burdaki objelerden bağımsız olduğu için classımızın objesini oluşturabiliriz.

        */
        SafeThread safeThread = new SafeThread();
        safeThread.threadlericalıstır();
    }
}
