|Interface|Basic functionality|Use cases|
|-----|:-----:|:-----:|
|Set |boolean add(E element); boolean remove(Object o); boolean contains(Object o)|Stores a bunch of unique elements|
|List|boolean add(E element); void add(int index, E element); boolean remove(Object o); E remove(int index); E get(int index); E set(int index, E element); boolean contains(Object o);|Useful to store bunch of elements, which can duplicates. Provide quick access by index. Can stores elements in order of insertion|
|Queue|boolean add(E element); boolean offer(E element); E remove(); E poll(); E element(); E peek();|Designed for holding elements prior by processing|
|Map|V put(K key, V value); V get(Object key); boolean containsKey(Object key); boolean containsValue(Object value); V remove(Object key); Set<K> keySet();boolean remove(Object key, Object value);|Maps keys to values. Can't contain duplicates keys. Can be viewed as set of keys, collection of values or set of key-value mappings.|