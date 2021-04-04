class StringManipulator{
    
    String findInitials(String name){
        int boslukSira = name.indexOf(' '); 
        String name1 = name.substring(0, boslukSira).toUpperCase(); 
        String name2 = name.substring(boslukSira + 1, name.length()).toUpperCase(); 
        String result = String.valueOf(name1.charAt(0)).concat(String.valueOf(name2.charAt(0))); 
        
        return result; 
    }
    
    String changeExtension(String filename, String ext){
        int noktaSira = filename.indexOf('.'); 
        String name1 = filename.substring(0, noktaSira); 
        String result = name1 + "." + ext; 
        
        return result;
    }
    
    public static void main(String[] args){
        StringManipulator str = new StringManipulator();
        System.out.println(str.findInitials("sena merdin"));
        System.out.println(str.changeExtension("hello.java", "ext"));
    }
    
}