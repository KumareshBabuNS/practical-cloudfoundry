package org.cloudfoundry.tools.io.local;

import java.io.File;

import org.cloudfoundry.tools.io.Folder;
import org.cloudfoundry.tools.io.JailedResourcePath;
import org.cloudfoundry.tools.io.local.LocalResourceStore.LocalFolderStore;
import org.cloudfoundry.tools.io.store.FolderStore;
import org.cloudfoundry.tools.io.store.StoredFolder;

/**
 * A {@link Folder} implementation backed by standard {@link File java.io.File}s.
 * 
 * @see LocalFile
 * 
 * @author Phillip Webb
 */
public class LocalFolder extends StoredFolder {

	private final LocalFolderStore store;

	/**
	 * Package level constructor used when accessing nested folders.
	 * 
	 * @param store the store
	 */
	LocalFolder(LocalFolderStore store) {
		this.store = store;
	}

	/**
	 * Create a new {@link LocalFolder} for the specified folder.
	 * 
	 * @param folder the underlying folder, eg '/home/username'
	 */
	public LocalFolder(String folder) {
		this(new java.io.File(folder));
	}

	/**
	 * Create a new {@link LocalFolder} for the specified folder.
	 * 
	 * @param folder the underlying folder
	 */
	public LocalFolder(java.io.File folder) {
		this.store = new LocalFolderStore(folder, new JailedResourcePath());
	}

	@Override
	protected FolderStore getStore() {
		return this.store;
	}

	/**
	 * Returns access to the underlying local {@link File}.
	 * 
	 * @return the underlying {@link File}
	 */
	public File getLocalFile() {
		return this.store.getFile();
	}
}
