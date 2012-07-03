package pt.ist.fenixframework.core;

import pt.ist.fenixframework.DomainObject;

/** This is the top-level class for every DomainObject. Each backend should provide a subclass of
 * this class, which provides a specific implementation of both
 * <code>DomainObject.getExternalId()</code> and <code>getOid()</code>.
 */
public abstract class AbstractDomainObject implements DomainObject {
    /** The implementation of this method is backend-specific.  It should be used within the *
    framework, whereas the <code>DomainObject.getExternalId()</code> should be used by the user of
    the * framework. */
    public abstract Object getOid();

    // protected AbstractDomainObject() {
    //     super();
    //     //smf: should we force this here with an abstract ensureOid()?
    //     // ensureOid();
    // }

    //smf: ???????
    // protected AbstractDomainObject(DomainObjectAllocator.OID oid) {
    //     // this constructor exists only as part of the allocate-instance
    //     // protocol
    //     this.oid = oid.oid;
    // }

    @Override
    public final int hashCode() {
	return super.hashCode();
    }

    @Override
    public final boolean equals(Object obj) {
	return super.equals(obj);
    }

    public static <T extends DomainObject> T fromOid(long oid) {
        return null;
    }

    // public static <T extends DomainObject> T fromOID(long oid) {
    //     DomainObject obj = Transaction.getCache().lookup(oid);

    //     if (obj == null) {
    //         obj = DomainObjectAllocator.allocateObject(oid);

    //         // cache object and return the canonical object
    //         obj = Transaction.getCache().cache(obj);
    //     }

    //     return (T) obj;
    // }

    // VersionedSubject getSlotNamed(String attrName) {
    //     Class myClass = this.getClass();
    //     while (myClass != Object.class) {
    //         try {
    //     	Field f = myClass.getDeclaredField(attrName);
    //     	f.setAccessible(true);
    //     	return (VersionedSubject) f.get(this);
    //         } catch (NoSuchFieldException nsfe) {
    //     	myClass = myClass.getSuperclass();
    //         } catch (IllegalAccessException iae) {
    //     	throw new Error("Couldn't find attribute " + attrName + ": " + iae);
    //         } catch (SecurityException se) {
    //     	throw new Error("Couldn't find attribute " + attrName + ": " + se);
    //         }
    //     }

    //     return null;
    // }

    // Object getCurrentValueFor(String attrName) {
    //     return getSlotNamed(attrName).getCurrentValue(this, attrName);
    // }

    // jvstm.VBoxBody addNewVersion(String attrName, int txNumber) {
    //     VersionedSubject vs = getSlotNamed(attrName);
    //     if (vs != null) {
    //         return vs.addNewVersion(attrName, txNumber);
    //     }

    //     System.out.println("!!! WARNING !!!: addNewVersion couldn't find the appropriate slot");
    //     return null;
    // }

    // public void readFromResultSet(java.sql.ResultSet rs) throws java.sql.SQLException {
    //     int txNumber = Transaction.current().getNumber();
    //     readSlotsFromResultSet(rs, txNumber);
    // }

    // protected abstract void readSlotsFromResultSet(java.sql.ResultSet rs, int txNumber) throws java.sql.SQLException;

    // public boolean isDeleted() {
    //     throw new UnsupportedOperationException();
    // }

    // protected void checkDisconnected() {
    // }

    // protected void handleAttemptToDeleteConnectedObject() {
    //     if (FenixFramework.getConfig().isErrorfIfDeletingObjectNotDisconnected()) {
    //         throw new Error("Trying to delete a DomainObject that is still connected to other objects: " + this);
    //     } else {
    //         System.out.println("!!! WARNING !!!: Deleting a DomainObject that is still connected to other objects: " + this);
    //     }	
    // }

    // protected void deleteDomainObject() {
    //     checkDisconnected();
    //     Transaction.deleteObject(this);
    // }

    // protected int getColumnIndex(final ResultSet resultSet, final String columnName, final Integer[] columnIndexes,
    //         final int columnCount) throws SQLException {
    //     if (columnIndexes[columnCount] == null) {
    //         synchronized (columnIndexes) {
    //     	if (columnIndexes[columnCount] == null) {
    //     	    int columnIndex = Integer.valueOf(resultSet.findColumn(columnName));
    //     	    columnIndexes[columnCount] = columnIndex;
    //     	}
    //         }
    //     }
    //     return columnIndexes[columnCount].intValue();
    // }

    // // serialization code
    // protected Object writeReplace() throws ObjectStreamException {
    //     return new SerializedForm(this);
    // }

    // private static class SerializedForm implements Serializable {
    //     private static final long serialVersionUID = 1L;

    //     // use string to allow future expansion of an OID
    //     private final String oid;

    //     SerializedForm(AbstractDomainObject obj) {
    //         this.oid = String.valueOf(obj.getOid());
    //     }

    //     Object readResolve() throws ObjectStreamException {
    //         long objOid = Long.parseLong(this.oid);
    //         return AbstractDomainObject.fromOID(objOid);
    //     }
    // }

    // public static <T extends DomainObject> T fromExternalId(String extId) {
    //     if (extId == null) {
    //         return null;
    //     } else {
    //         return AbstractDomainObject.<T> fromOID(Long.valueOf(extId));
    //     }
    // }

    // protected void doCheckDisconnectedAction(java.util.List<String> relationList) {
    //     for(String relation : relationList) {
    //         System.out.println("Relation not disconnected" + relation);
    //     }
    // }

    @Override
    public String toString() {
	return getClass().getName() + ":" + getExternalId();
    }
}
