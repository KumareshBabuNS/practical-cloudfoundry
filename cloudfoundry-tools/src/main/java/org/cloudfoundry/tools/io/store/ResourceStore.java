package org.cloudfoundry.tools.io.store;

import org.cloudfoundry.tools.io.File;
import org.cloudfoundry.tools.io.Folder;
import org.cloudfoundry.tools.io.JailedResourcePath;
import org.cloudfoundry.tools.io.Resource;

/**
 * Base interface for {@link FileStore} and {@link FolderStore}.
 * 
 * @see FileStore
 * @see FolderStore
 * 
 * @author Phillip Webb
 */
public interface ResourceStore {

	/**
	 * Return the path of the current resource.
	 * 
	 * @return the path.
	 */
	JailedResourcePath getPath();

	/**
	 * Return an existing resource for the specified path or <tt>null</tt> if no resource exists.
	 * 
	 * @param path the path
	 * @return the resource
	 */
	Resource getExisting(JailedResourcePath path);

	/**
	 * Return a folder for the specified path.
	 * 
	 * @param path the path
	 * @return the folder
	 */
	Folder getFolder(JailedResourcePath path);

	/**
	 * Return a file for the specified path.
	 * 
	 * @param path the path
	 * @return the file
	 */
	File getFile(JailedResourcePath path);

	/**
	 * Returns <tt>true</tt> if the resource exists.
	 * 
	 * @return if the resource exists.
	 */
	boolean exists();

	/**
	 * Rename the resource.
	 * 
	 * @param name the new name
	 * @return the resource of the renamed item
	 */
	Resource rename(String name);

	/**
	 * Delete the resource.
	 */
	void delete();

	/**
	 * Create the resource when it does not exist.
	 */
	void create();

	/**
	 * Implementations must provide a suitable hashcode based on the underlying resource.
	 * 
	 * @return the hash code
	 */
	@Override
	public int hashCode();

	/**
	 * Implementations must provide a suitable equals based on the underlying resource.
	 * 
	 * @param obj the object to compare
	 * @return <tt>true</tt> if the items are equal
	 */
	@Override
	public boolean equals(Object obj);

}
