package net.chiappone.util;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Kurtis Chiappone
 * @date 10/9/2016
 */
public class ClassUtil {

    private static final Set<String> collectionTypes = new HashSet<String>();
    private static final Set<String> primitiveTypes = new HashSet<String>();

    static {

        // Collection Interfaces
        collectionTypes.addAll(
                        Arrays.asList( "Collection", "BeanContext", "BeanContextServices", "BlockingQueue", "List",
                                        "Queue", "Set", "SortedSet" ) );

        // Collection Implementations
        collectionTypes.addAll(
                        Arrays.asList( "AbstractCollection", "AbstractList", "AbstractQueue", "AbstractSequentialList",
                                        "AbstractSet", "ArrayBlockingQueue", "ArrayList", "AttributeList",
                                        "BeanContextServicesSupport", "ConcurrentLinkedQueue", "CopyOnWriteArrayList",
                                        "CopyOnWriteArraySet", "DelayQueue", "EnumSet", "HashSet", "JobStateReasons",
                                        "LinkedBlockingQueue", "LinkedHashSet", "LinkedList", "PriorityBlockingQueue",
                                        "PriorityQueue", "RoleList", "RoleUnresolvedList", "Stack", "SynchronousQueue",
                                        "TreeSet", "Vector" ) );

        // Primitives
        primitiveTypes.addAll( Arrays.asList( "byte", "char", "character", "int", "integer", "double", "float", "long",
                        "short", "boolean", "string" ) );

    }

    /**
     * Returns the class name from the fully-qualified name.
     *
     * @param fullyQualifiedName
     * @return String
     */
    public static String getClassName( String fullyQualifiedName ) {

        return fullyQualifiedName.substring( fullyQualifiedName.lastIndexOf( "." ) + 1 );

    }

    /**
     * Returns the fully-qualified class name taken from a file and package.
     *
     * @param javaFile
     * @param packageName
     * @return String
     */
    public static String getFullyQualifiedName( File javaFile, String packageName ) {

        String name = javaFile.getName();
        return packageName + "." + name.substring( 0, name.indexOf( "." ) );

    }

    /**
     * Extracts a parameterization. Returns null if the passed in String does
     * not contain a parameterization.
     *
     * @param parameterizedClass
     * @return String
     */
    public static String extractParameterization( String parameterizedClass ) {

        if ( !isParameterized( parameterizedClass ) )
            return null;

        return parameterizedClass
                        .substring( parameterizedClass.indexOf( "<" ) + 1, parameterizedClass.lastIndexOf( ">" ) );

    }

    /**
     * Determines if the passed in String representation of a class is a
     * Collection type.
     *
     * @param clazz
     * @return boolean
     */
    public static boolean isCollection( String clazz ) {

        if ( isParameterized( clazz ) ) {
            clazz = removeParameterization( clazz );
        }

        if ( clazz.contains( "java.util." ) ) {
            clazz = clazz.substring( 10 );
        }

        return collectionTypes.contains( clazz );

    }

    /**
     * Determines if the passed in String representation of a class is
     * parameterized.
     *
     * @param clazz
     * @return boolean
     */
    public static boolean isParameterized( String clazz ) {

        return clazz != null && clazz.contains( "<" ) && clazz.contains( ">" );

    }

    /**
     * Determines if the passed in String representation of a class is a
     * primitive type or a primitive type wrapper.
     *
     * @param clazz
     * @return boolean
     */
    public static boolean isPrimitiveType( String clazz ) {

        clazz = clazz.toLowerCase();

        if ( clazz.contains( "java.lang." ) ) {
            clazz = clazz.substring( 10 );
        }

        return primitiveTypes.contains( clazz );

    }

    /**
     * Removes the parameterized type from the given String representation of a
     * class.
     *
     * @param clazz
     * @return String
     */
    public static String removeParameterization( String clazz ) {

        return clazz.substring( 0, clazz.indexOf( "<" ) );

    }

}
