// js/db.js — IndexedDB wrapper for GradeVault
const DB = (() => {
  let _db = null;
  const DB_NAME = 'GradeVaultDB';
  const DB_VERSION = 1;
  const STORE = 'grades';

  function open() {
    return new Promise((resolve, reject) => {
      if (_db) return resolve(_db);
      const req = indexedDB.open(DB_NAME, DB_VERSION);

      req.onupgradeneeded = e => {
        const db = e.target.result;
        if (!db.objectStoreNames.contains(STORE)) {
          const store = db.createObjectStore(STORE, { keyPath: 'id', autoIncrement: true });
          store.createIndex('name', 'name', { unique: false });
          store.createIndex('subject', 'subject', { unique: false });
          store.createIndex('date', 'date', { unique: false });
        }
      };

      req.onsuccess = e => { _db = e.target.result; resolve(_db); };
      req.onerror = e => reject(e.target.error);
    });
  }

  function getAll() {
    return new Promise((resolve, reject) => {
      const tx = _db.transaction(STORE, 'readonly');
      const req = tx.objectStore(STORE).getAll();
      req.onsuccess = e => resolve(e.target.result);
      req.onerror = e => reject(e.target.error);
    });
  }

  function getById(id) {
    return new Promise((resolve, reject) => {
      const tx = _db.transaction(STORE, 'readonly');
      const req = tx.objectStore(STORE).get(id);
      req.onsuccess = e => resolve(e.target.result);
      req.onerror = e => reject(e.target.error);
    });
  }

  function save(data, id) {
    return new Promise((resolve, reject) => {
      const tx = _db.transaction(STORE, 'readwrite');
      const store = tx.objectStore(STORE);
      const record = id ? { ...data, id } : data;
      const req = id ? store.put(record) : store.add(record);
      req.onsuccess = () => resolve(req.result);
      req.onerror = e => reject(e.target.error);
    });
  }

  function remove(id) {
    return new Promise((resolve, reject) => {
      const tx = _db.transaction(STORE, 'readwrite');
      const req = tx.objectStore(STORE).delete(id);
      req.onsuccess = () => resolve();
      req.onerror = e => reject(e.target.error);
    });
  }

  async function seed() {
    const existing = await getAll();
    if (existing.length > 0) return;
    const samples = [
      { name: 'Ananya Sharma', subject: 'Mathematics', score: 94, date: '2026-03-15', notes: 'Final exam' },
      { name: 'Rajan Mehta', subject: 'Physics', score: 78, date: '2026-03-15', notes: 'Midterm' },
      { name: 'Priya Nair', subject: 'Chemistry', score: 88, date: '2026-03-14', notes: '' },
      { name: 'Kiran Patel', subject: 'Mathematics', score: 62, date: '2026-03-13', notes: 'Quiz 3' },
      { name: 'Divya Rao', subject: 'Biology', score: 96, date: '2026-03-12', notes: 'Practical exam' },
      { name: 'Arjun Singh', subject: 'Physics', score: 55, date: '2026-03-11', notes: 'Needs support' },
      { name: 'Meena Iyer', subject: 'Chemistry', score: 83, date: '2026-03-10', notes: '' },
      { name: 'Suresh Kumar', subject: 'Mathematics', score: 71, date: '2026-03-09', notes: 'Unit test' },
    ];
    for (const s of samples) await save(s);
  }

  return { open, getAll, getById, save, remove, seed };
})();
