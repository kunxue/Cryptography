import java.io.*;

public class rsaExample
{
    private int p=0;
    private int q=0;
    private long n=0;
    private long m=0;

    private long public_key=0;//public key
    private long private_key=0;//private key

    private long text=0;//plain text
    private long secretword=0;//secret text
    private long word=0;//text decripted

    //wheter it is a prime number
    public boolean primenumber(long t)
    {
        long k=0;
        k=(long)Math.sqrt((double)t);
        boolean flag=true;
        outer:for(int i=2;i<=k;i++)
        {
            if((t%i)==0)
            {
                flag = false;
                break outer;
            }
        }
        return flag;
    }
    //input pq
    public void inputPQ()throws Exception
    {
        do{
                System.out.println("Please input prime p: ");
                BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
                String br=stdin.readLine();
                this.p=Integer.parseInt(br);
         }
        while(!

primenumber(this.p));
        do{
            System.out.println("Please input prime q: ");
            BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
            String br=stdin.readLine();
            this.q=Integer.parseInt(br);
        }
        while(!primenumber(this.q));
        this.n=this.p*this.q;
        this.m=(p-1)*(q-1);
        System.out.println("p*q："+this.n);
        System.out.println("m=(p-1)(q-1)："+this.m);
    }
    //
    public long gcd(long a,long b)
    {
        long gcd;
        if(b==0)
            gcd=a;
        else
            gcd=gcd(b,a%b);
        System.out.println("gcd:"+gcd);
        return gcd;

    }
    //
    public void getPublic_key()throws Exception
    {
        do{
            System.out.println("please input e： ");
            BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
            String br=stdin.readLine();
            this.public_key=Long.parseLong(br);
        }while((this.public_key >= this.m)  
                &&(this.gcd(this.m,this.public_key)!=1));


        System.out.println("public key is ："+this.public_key);
    }
    //
    public void getPrivate_key()
    {
        long value=1;
        outer:for(long i=1;;i++)
        {
            value=i*this.m+1;
            System.out.println("value:  "+value);
            if((value%this.public_key==0)&& (value/this.public_key < this.m))
            {
                this.private_key=value/this.public_key;
                break outer;
            }
        }
        System.out.println("private key is："+this.private_key);
    }
    //
    public void getText()throws Exception
    {
        System.out.println("please input plaintext：");
        BufferedReader stdin=new BufferedReader(new InputStreamReader(System.in));
        String br=stdin.readLine();
        this.text=Long.parseLong(br);

 }
    //
    public long colum(long y,long n,long key)
    {
        long mul;
        if(key==1)
            mul=y%n;
        else
            mul=y*this.colum(y,n,key-1)%n;
        return mul;
    }

    //
    public void pascolum()throws Exception
    {
        this.getText();
        System.out.println("plaintext is : "+this.text);
        //加密
        this.secretword=this.colum(this.text,this.n,this.public_key);
        System.out.println("ciphertext is ："+this.secretword);
        //解密
        this.word=this.colum(this.secretword,this.n,this.private_key);
        System.out.println("original plaintext is ："+this.word);

    }
    public static void main(String []args)throws Exception
    {
        rsaExample t = new rsaExample();
        t.inputPQ();


        t.getPublic_key();
        t.getPrivate_key();
        t.pascolum();
    }

}
