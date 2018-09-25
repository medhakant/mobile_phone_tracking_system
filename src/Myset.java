public class Myset {

    /* Start with the empty list. */
    LinkedList llist = new LinkedList();

    public  Boolean IsEmpty(){
        if (llist.head==null)
            return true;
        else
            return false;
    }

    public  Boolean IsMember(Object e){
        LinkedList.Node n =llist.head;
        while (n != null)
        {
           if(n.data==e)
               return true;
           n=n.next;
        }
        return false;

    }

    public void Insert(Object o){
        LinkedList.Node nrear =llist.head;
        if (nrear!=null){
            LinkedList.Node nfront =nrear.next;
            while (nfront!=null){
                if (nrear.data==o)
                    return;
                nfront=nfront.next;
                nrear=nrear.next;
            }
            LinkedList.Node newNode = new LinkedList.Node(o);
            nrear.next=newNode;
        }
        else
            llist.head =new LinkedList.Node(o);

    }

    public void Delete(Object o){
        try {
            LinkedList.Node nrear = llist.head;
            LinkedList.Node nfront = nrear.next;
            if (nrear.data ==o){
                llist.head=nfront;
                return ;
            }
            while (nfront!=null){
                if (nfront.data==o){
                    nrear.next = nfront.next;
                    nrear.data = nfront.data;
                    return;
                }
                nfront=nfront.next;
                nrear=nrear.next;
            }

        }catch(NullPointerException e) {}
    }

    public Myset Union(Myset a){
        Myset u = new Myset();
        LinkedList.Node n = llist.head;
        while (n!=null){
            u.Insert(n.data);
            n=n.next;
        }
        n = a.llist.head;
        while (n!=null){
            u.Insert(n.data);
            n=n.next;
        }
        return u;
    }

    public Myset Intersection(Myset a){
        Myset i = new Myset();
        LinkedList.Node n = a.llist.head;
        while (n!=null){
            if (IsMember(n.data)==true)
                i.Insert(n.data);

            n=n.next;
        }
        return i;
    }

    public static void main(String[] args)
    {

        Myset m=new Myset();
        m.Insert(5);
        m.Insert(6);
        m.Insert(7);
        m.Insert(5);

        Myset a = new Myset();
        a.Insert(1);
        a.Insert(2);
        a.Insert(3);
        a.Insert(5);

        LinkedList.Node n = m.Intersection(a).llist.head;
        while (n!=null){
            System.out.println(n.data);
            n=n.next;
        }

        return ;
    }
}
