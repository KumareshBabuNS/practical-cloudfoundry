package org.cloudfoundry.tools.io.virtual;

import org.cloudfoundry.tools.io.File;
import org.cloudfoundry.tools.io.Folder;
import org.cloudfoundry.tools.io.store.FolderStore;
import org.cloudfoundry.tools.io.store.StoredFolder;
import org.cloudfoundry.tools.io.virtual.VirtualResourceStore.VirtualFolderStore;
import org.springframework.util.Assert;

/**
 * A virtual {@link Folder} that exists only in memory. Virtual folders provide a convenient method for manipulating
 * existing {@link File}s and {@link Folder}s without needing to create physical copies. Memory consumption for
 * {@link VirtualFile}s is kept to a minimum by only storing data when it is changed.
 * 
 * @author Phillip Webb
 */
public class VirtualFolder extends StoredFolder {

	private final VirtualFolderStore store;

	public VirtualFolder() {
		this.store = new VirtualFolderStore();
	}

	VirtualFolder(VirtualFolderStore store) {
		Assert.notNull(store, "Store must not be null");
		this.store = store;
	}

	@Override
	protected FolderStore getStore() {
		return this.store;
	}

}
