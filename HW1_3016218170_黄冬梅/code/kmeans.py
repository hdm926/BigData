#k聚类
#coding=utf-8
import numpy as np
import matplotlib.pyplot as plt
from sklearn.cluster import KMeans

#从磁盘读取城市经纬度数据
X = []
f = open('C:\\Users\\h\\Desktop\\data2d2.txt')
for v in f:
    X.append([float(v.split('  ')[0].strip()), float(v.split('  ')[1].strip())])
#转换成numpy array
X = np.array(X)
#类簇的数量
n_clusters = 5
#现在把数据和对应的分类书放入聚类函数中进行聚类
cls = KMeans(n_clusters).fit(X)
#X中每项所属分类的一个列表
cls.labels_
#画图
markers = ['x', 'o', '*','+','1']
color=['r','b','g','y','c']
for i in range(n_clusters):
  members = cls.labels_ == i
  plt.scatter(X[members, 0], X[members, 1], s=60, marker=markers[i], c=color[i], alpha=0.5)
plt.title(' ')
plt.show()