#created by mica
import tensorflow as tf
import numpy as np

labels_offset = 0
train_dir = '/home/mica/fault_prediction/TFlogs'
NNIL = 0
NNOL = 0
NNHL = []
NH = 4
LearningRate = 0
BatchSize = 10

traindataset = []

testdataset = []
def get_train_(traindataset):
	temfuturelist = []
	temhistoricallist = []
	for i in range (len(traindataset)):
		data = []
		data = traindataset[i]
		temfuturelist[i] = data.pop()
		temhistoricallist[i] = data

	return np.array(temhistoricallist),  np.array(temfuturelist)
validationdataset = []
def get_val_(validationdataset):
	temfuturelist = []
	temhistoricallist = []
	for i in range (len(validationdataset)):
		data = []
		data = traindataset[i]
		temfuturelist[i] = data.pop()
		temhistoricallist[i] = data

	return np.array(temhistoricallist), np.array(temfuturelist)

def get_test_(testdataset):
	temfuturelist = []
	temhistoricallist = []
	for i in range (len(testdataset)):
		data = []
		data = traindataset[i]
		temfuturelist[i] = data.pop()
		temhistoricallist[i] = data

	return np.array(temhistoricallist), np.array(temfuturelist)

def get_batchdata(traintemhistorical,traintemfuture,iteration):
	batchnumber = np.size(traintemhistorical,0) % iteration
	batchtemhistorical = traintemhistorical[:np.size(traintemhistorical,0)-iteration,:]
	batchtemfuture = traintemfuture[:np.size(traintemfuture,0)-iteration,:]
	return np.split(batchtemhistorical,iteration), np.split(batchtemfuture,iteration)
def get_iteration(traintemhistorical,BatchSize):
	return np.size(traintemhistorical,0) // BatchSize

def add_layer(inputs,in_size,out_size,layer_n,activation_function=None):
	layer_name="fully_connected%s" % layer_n
	with tf.name_scope(layer_name):
		with tf.name_scope('weights'):
			weights = tf.Variable(tf.truncated_normal([in_size,out_size],stddev=0.1))
			tf.summary.histogram(layer_name+"/weights",weights)
		with tf.name_scope('biases'):
			biases = tf.Variable(tf.constant(0.1,[1,out_size]))
			tf.summary.histogram(layer_name+"/biases",biases)
		with tf.name_scope('Wx_plus_b'):
			Wx_plus_b = tf.matmul(inputs,weights)+biases
			tf.summary.histogram(layer_name+"/Wx_plus_b",Wx_plus_b)
		if activation_function is None:
			outputs = Wx_plus_b
		else:
			outputs = activation_function(Wx_plus_b)
		tf.summary.histogram(layer_name+"/outputs",outputs)
		return outputs

with tf.name_scope('inputs'):
	xs = tf.placeholder(tf.float32,shape=([None,NNIL]),name='temhistorical')
	ys = tf.placeholder(tf.float32,shape=([None,NNOL]),name='temfuture')
for i in range (NH):
	if i == 0:
		net = add_layer(xs,NNIL,NNHL[i],layer_n=i+1,activation_function=tf.nn.relu)
	if i>0 and i<NH-1:
		net = add_layer(net,NNHL[i-1],NNHL[i],layer_n=i+1,activation_function=tf.nn.relu)
	if i == NH-1:
		net = add_layer(net,NNHL[i-1],NNHL[i],layer_n=i+1,activation_function=tf.nn.relu)
		temfuturepre = add_layer(net,NNHL[i],NNOL,layer_n=i+1,activation_function=None)
with tf.name_scope('loss'):
	loss = tf.reduce_mean(tf.reduce_sum(tf.square(ys-temfuturepre)))
	tf.summary.scalar('loss',loss)
with tf.name_scope('train'):
	train_step = tf.train.AdamOptimizer(learning_rate=LearningRate).minimize(loss)

init = tf.initialize_all_variables()
sess = tf.Session()

traintemhistorical,traintemfuture = get_train_(traindataset)
iteration = get_iteration(traintemhistorical,BatchSize)
batchdatahis,batchdatafu = get_batchdata(traintemhistorical,traintemfuture,iteration)
vadidation_accuracy = tf.reduce_mean(tf.reduce_sum(tf.square(ys-temfuturepre)))
merged = tf.summary.merge_all()

writer = tf.summary.FileWriter(train_dir,sess.graph)
sess.run(init)


'''for i in range(epoch):
	for j in range(iteration):
		sess.run(train_step,feed_dict={xs:batchdatahis[j],ys:batchdatafu[j]})
		if j % 10 == 0:
			print "iteration %d, loss %g"%(j,loss)
	vadidate_accuracy = sess.run(vadidation_accuracy,feed_dict={xs:valtemhistorical,ys:valtemfuture})
	result = sess.run(merged,feed_dict={xs:valtemhistorical,ys:valtemfuture})
	writer.add_summary(result,i)
'''
for i in range(1000):
    sess.run(train_step,feed_dict={xs:traintemhistorical,ys:traintemfuture})
    if i % 100 == 0:
        print ("iteration %d, loss %g"%(i,loss))
#	result = sess.run(merged,feed_dict={xs:valtemhistorical,ys:valtemfuture})
#        writer.add_summary(result,i)




