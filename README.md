# hello-spring-data

Spring Data's mission is to provide a familiar and consistent, Spring-based programming model for data access 
while still retaining the special traits of the underlying data store.

## Repository 接口

- `public interface Repository<T, ID> {}`

Repository 接口是 Spring Data 的核心接口，不提供任何实现方法。是一个空接口，也称为标记接口。

**(1) CrudRepository**：继承 Repository，实现了 CRUD 相关方法。
- `<S extends T> S save(S entity);`  保存实体
- `<S extends T> Iterable<S> saveAll(Iterable<S> entities);`   保存实体集合
- `Optional<T> findById(ID id);`   根据 id 查询实体
- `boolean existsById(ID id);`   返回是否存在对应 id 的实体
- `Iterable<T> findAll();`   返回所有实体
- `Iterable<T> findAllById(Iterable<ID> ids);`   根据 ids 查询实体
- `long count();`  返回实体数
- `void deleteById(ID id);`  根据 id 删除实体
- `void delete(T entity);`  根据实体删除
- `void deleteAll(Iterable<? extends T> entities);`  根据实体列表删除
- `void deleteAll();`  删除所有实体

**(2) PagingAndSortingRepository**：继承 CurdRepository，实现了分页排序相关方法
- `Iterable<T> findAll(Sort sort);`  查询所有实体并按照 sort 排序
- `Page<T> findAll(Pageable pageable);`  分页查询

## JPA 方法关键字定义规则

参考[官方文档][2]

| Keyword | Sample | JPQL snippet |
| --- | --- | --- |
| And | findByNameAndAge | ... where x.name = ?1 and x.age = ?2  |
| Or | findByNameOrAge | ... where x.name = ?1 or x.age = ?2 |
| Is,Equals | findByName,findByNameIs,findByNameEquals | … where x.name = ?1 |
| Between | findByStartDateBetween | … where x.startDate between ?1 and ?2 |
| LessThan | findByAgeLessThan | … where x.age < ?1 |
| LessThanEqual | findByAgeLessThanEqual | … where x.age <= ?1 |
| GreaterThan | findByAgeGreaterThan | … where x.age > ?1 |
| GreaterThanEqual | findByAgeGreaterThanEqual | … where x.age >= ?1 |
| After | findByStartDateAfter | … where x.startDate > ?1 |
| Before | findByStartDateBefore | … where x.startDate < ?1  |
| IsNull | findByAgeIsNull | … where x.age is null |
| IsNotNull,NotNull | findByAge(Is)NotNull | … where x.age is not null  |
| Like | findByNameLike | … where x.name like ?1  |
| NotLike | findByNameNotLike | … where x.name not like ?1  |
| StartingWith | findByNameStartingWith | … where x.name like ?1 (parameter bound with appended %) |
| EndingWith | findByNameEndingWith | … where x.name like ?1 (parameter bound with prepended %) |
| Containing | findByNameContaining | … where x.name like ?1 (parameter bound wrapped in %) |
| OrderBy | findByNameOrderByAgeDesc | … where x.name = ?1 order by x.age desc |
| Not | findByNameNot | … where x.lastname <> ?1 |
| In | findByAgeIn(`Collection<Age> ages`) | … where x.age in ?1 |
| NotIn | findByAgeNotIn(`Collection<Age> ages`) | … where x.age not in ?1 |
| True | findByActiveTrue() | … where x.active = true |
| False | findByActiveFalse() | … where x.active = false |
| IgnoreCase | findByNameIgnoreCase | … where UPPER(x.name) = UPPER(?1) |

其中 **find** 关键字也可以为 **get** (例如：getByName) ，使用关键字命名方法会导致名称特别长，并且很难实现复杂查询，因此还可以通过注解的方式扩展。

## 参考

1. [轻松愉快之玩转SpringData](https://www.imooc.com/learn/821)
2. [CSDN 博客](https://blog.csdn.net/leifchen90/article/details/84110821)