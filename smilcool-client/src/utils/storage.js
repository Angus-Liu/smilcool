// 封装 localStorage
const storage = {
  set(key, value) {
    localStorage.setItem(key, JSON.stringify(value))
  },
  get(key) {
    return JSON.parse(localStorage.getItem(key))
  },
  remove(key) {
    localStorage.removeItem(key)
  },
  clear() {
    localStorage.clear();
  }
};
export default storage
