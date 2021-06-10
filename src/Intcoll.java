import java.util.*;

public class Intcoll
{
   private int howmany;
   private ListNode c;

    /**
     * Generic constructor for a singly linked list
     */
   public Intcoll()
   {
      c=null; howmany = 0;
   }

    /**
     * Alternate constructor for singly linked list
     * @param i Since size does not matter this parameter is ignored
     */
   public Intcoll(int i)
   {
      c=null; howmany = 0;
   }

    /**
     * Converts target Intcoll4 object into an exact copy of the argument Intcoll4 object
     * @param obj Accepts Intcoll4 object as argument parameter
     */
   public void copy(Intcoll obj)
   {
       if (this!=obj){
           howmany=obj.howmany;
           if (obj.c==null)
               c=null;
           else {
               c=new ListNode(obj.c.info, null);
               ListNode p=c, q=obj.c;;
               while (q.link!=null) {
                   q=q.link;
                   p.link= new ListNode(q.info, null);
                   p=p.link;
               }
           }
   }
         
   }

    /**
     * Inserts a new integer element to Intcoll4 object
     *Will not add if the integer is already contained in the linked list
     * @param i accepts an integer as the argument parameter
     */
   public void insert(int i)
   {
      ListNode p=c;
      while ((p!=null)&&(p.info!=i)) p=p.link;
      if (p==null)
      {
         p=new ListNode(i, c);
         p.info=i;
         p.link=c;
         c=p;
         howmany++;
      }
   }

    /**
     * Removes integer from Intcoll4 object if it is currently contained in the linked list
     * @param i accepts integer as argument parameter
     */
   public void omit(int i)
   {
      ListNode p=c; ListNode pred=null;
          while ((p!=null)&&(p.info!=i)) {pred=p; p=p.link;}
          if (p!=null)
          {
              if (pred!=null) pred.link=p.link;
              else c=p.link;
              howmany--;
          }
   }

    /**
     * Checks if integer is currently being stored in the linked list
     * @param i accepts an integer as the argument parameter
     * @return returns boolean true if integer is stored in linked list, else false
     */
   public boolean belongs(int i)
   {
      ListNode p=c;
      while ((p!=null)&&(p.info!= i)) p=p.link;
      return (p!=null);
   }

    /**
     * Checks how many individual integer elements are currently stored in the linked list
     * @return returns integer value
     */
   public int get_howmany() {return howmany;}

    /**
     * prints linked list in order, 1 integer element per line
     */
   public void print()
   {
      ListNode p=c;
      System.out.println();
      while (p!=null)
      {
         System.out.println(p.info); p=p.link;
      }
   }

    /**
     * Checks if one Intcoll4 object is currently storing the same elements of the argument Intcoll4 object without
     * respect to order
     * @param obj accepts Intcoll4 object as argument parameter
     * @return returns boolean true if both contain the same elements (w/o respect to order), else returns false
     */
   public boolean equals(Intcoll obj)
   {
       ListNode p=c;
       boolean result = (howmany==obj.howmany);
       while ((p!=null) && result) {
           result = obj.belongs(p.info);
           p=p.link;

   }
       return result;

   }
   public int sum(){
       int sum = 0;
       ListNode p=c;
       while (p!=null){
           sum = sum + p.info;
           p=p.link;
       }

       return sum;
   }

   // The inner class for ListNode
      private class ListNode
      {
           private int info;
           private ListNode link;

           public ListNode()
           {
               info=0; link=null;
           }

           public ListNode(int i, ListNode p)
           {
               info=i; link=p;
           }
      }

 }
