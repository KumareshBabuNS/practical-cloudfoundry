package org.cloudfoundry.tools.io.mongo;

import org.cloudfoundry.tools.io.Folder;
import org.cloudfoundry.tools.io.JailedResourcePath;
import org.cloudfoundry.tools.io.mongo.MongoResourceStore.MongoFolderStore;
import org.cloudfoundry.tools.io.store.FolderStore;
import org.cloudfoundry.tools.io.store.StoredFolder;
import org.springframework.util.Assert;

import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;

/**
 * A {@link Folder} implementation backed by a mongo {@link GridFS}.
 * 
 * @see MongoFile
 * 
 * @author Phillip Webb
 */
public class MongoFolder extends StoredFolder {

	private final MongoFolderStore store;

	/**
	 * Package level constructor used by {@link MongoResourceStore} when accessing nested folders.
	 * 
	 * @param store the store
	 */
	MongoFolder(MongoFolderStore store) {
		this.store = store;
	}

	/**
	 * Create a new {@link MongoFolder} using rhe specified mongo database. The data will be stored in
	 * {@link GridFS#DEFAULT_BUCKET defaul bucket}.
	 * 
	 * @param db the mongo database
	 */
	public MongoFolder(DB db) {
		this(db, GridFS.DEFAULT_BUCKET);
	}

	/**
	 * Create a new {@link MongoFolder} using the specified mongo database and bucket.
	 * 
	 * @param db the mongo database
	 * @param bucket the bucket
	 */
	public MongoFolder(DB db, String bucket) {
		Assert.notNull(db, "DB must not be null");
		Assert.notNull(bucket, "Bucket must not be null");
		GridFS fs = new GridFS(db, bucket);
		this.store = new MongoFolderStore(fs, new JailedResourcePath());
	}

	@Override
	protected FolderStore getStore() {
		return this.store;
	}
}
